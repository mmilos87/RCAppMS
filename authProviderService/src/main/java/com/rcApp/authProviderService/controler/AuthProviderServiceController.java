package com.rcApp.authProviderService.controler;

import com.rcApp.authProviderService.exception.DeviceAndLocationException;
import com.rcApp.authProviderService.helpers.mappers.AppUserMapper;
import com.rcApp.authProviderService.models.AppUserDTO;
import com.rcApp.authProviderService.models.AuthValidateJwtDTO;
import com.rcApp.authProviderService.models.RcAuthenticationRequest;
import com.rcApp.authProviderService.security.jwt.JwtTokenHelper;
import com.rcApp.authProviderService.services.AuthProviderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class AuthProviderServiceController {

    private AuthProviderService authProviderService;
    private JwtTokenHelper jwtTokenHelper;

    @PostMapping("login")
    public String login(@RequestBody RcAuthenticationRequest rcAuthenticationRequest,
                        HttpServletResponse response, HttpServletRequest request) throws IOException,
            DeviceAndLocationException, IllegalStateException, UsernameNotFoundException {
        AppUserDTO appUserDTO = authProviderService.login(rcAuthenticationRequest, request);
        String token = jwtTokenHelper.generateToken(AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO));
        response.addHeader(jwtTokenHelper.getJwtConfig().getAuthorizationHeaders(),
                jwtTokenHelper.getJwtConfig().getTokenPrefix() + token);
        return token;
    }

    @PostMapping("validateToken")
    public String verifyJwt(@RequestBody AuthValidateJwtDTO authValidateJwtDTO) throws IllegalStateException {
        return authProviderService.verifyAndRefreshJwt(authValidateJwtDTO);

    }
}
