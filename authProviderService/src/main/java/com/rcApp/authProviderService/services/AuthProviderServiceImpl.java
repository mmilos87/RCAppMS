package com.rcApp.authProviderService.services;

import com.google.common.base.Strings;
import com.rcApp.authProviderService.exception.DeviceAndLocationException;
import com.rcApp.authProviderService.feignClients.AppUserClient;
import com.rcApp.authProviderService.feignClients.DeviceAndLocationClient;
import com.rcApp.authProviderService.feignClients.LoginAttemptClient;
import com.rcApp.authProviderService.helpers.enums.AppMessages;
import com.rcApp.authProviderService.helpers.enums.AppUserRole;
import com.rcApp.authProviderService.models.AppUserDTO;
import com.rcApp.authProviderService.models.AuthValidateJwtDTO;
import com.rcApp.authProviderService.models.DeviceAndLocationDTO;
import com.rcApp.authProviderService.models.RcAuthenticationRequest;
import com.rcApp.authProviderService.security.jwt.JwtConfig;
import com.rcApp.authProviderService.security.jwt.JwtTokenHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

import static com.rcApp.authProviderService.helpers.enums.AppMessages.DEVICE_IS_ENABLED;

@Service
@AllArgsConstructor
@Log4j2
public class AuthProviderServiceImpl implements AuthProviderService {
    private AppUserClient appUserClient;
    private BCryptPasswordEncoder passwordEncoder;
    private DeviceAndLocationClient deviceAndLocationClient;
    private LoginAttemptClient loginAttemptClient;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final JwtTokenHelper jwtTokenHelper;

    @Override
    public String verifyAndRefreshJwt(AuthValidateJwtDTO authValidateJwtDTO) {
        if (Strings.isNullOrEmpty(authValidateJwtDTO.getAuthToken()) ||
                !authValidateJwtDTO.getAuthToken().startsWith(jwtConfig.getTokenPrefix())) {
            throw new IllegalStateException("Invalid token format");
        }
        try {
            String token = authValidateJwtDTO.getAuthToken().replace(jwtConfig.getTokenPrefix(), "");
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            AppUserRole tokenRole = jwtTokenHelper.extractUserRoleFromJwt(body);
            if (!authValidateJwtDTO.getAllowedRoles().contains(tokenRole.name()))
                throw new IllegalStateException("You are not authorized for this service!");
            return jwtConfig.getTokenPrefix() + jwtTokenHelper.refreshToken(body);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());

        }
    }

    @Override
    public AppUserDTO login(RcAuthenticationRequest rcAuthenticationRequest, HttpServletRequest request)
            throws IOException, DeviceAndLocationException {
        AppUserDTO appUserDTO = Optional.ofNullable(appUserClient.loadUserByEmail(rcAuthenticationRequest.getUsername()))
                .orElseThrow(() -> new UsernameNotFoundException("user not exit"));
        if (!passwordEncoder.matches(rcAuthenticationRequest.getPassword(), appUserDTO.getPassword())) {
            loginAttemptClient.failureLoginAttempt(appUserDTO);
            throw new IllegalStateException("password is wrong");
        }
        if (!appUserDTO.getEnabled()) {
            throw new IllegalStateException("not enabled");
        }
        if (appUserDTO.getLocked()) {
            throw new IllegalStateException("user is locked");
        }
        AppMessages verifyResult = deviceAndLocationClient.verifyDevice(new DeviceAndLocationDTO(request, appUserDTO));
        if (!verifyResult.equals(DEVICE_IS_ENABLED)) {
            loginAttemptClient.failureLoginAttempt(appUserDTO);
            throw new DeviceAndLocationException(verifyResult);
        }
        loginAttemptClient.successLoginAttempt(appUserDTO);
        return appUserDTO;
    }
}
