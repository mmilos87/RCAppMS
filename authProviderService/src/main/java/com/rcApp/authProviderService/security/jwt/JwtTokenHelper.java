package com.rcApp.authProviderService.security.jwt;

import com.rcApp.authProviderService.entitety.AppUser;

import com.rcApp.authProviderService.helpers.enums.AppUserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.rcApp.authProviderService.helpers.enums.JwtTokenFields.*;

@AllArgsConstructor
@Component
@Getter
public class JwtTokenHelper {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private static final int MIN_TO_MILLISECONDS = 60000;

    public String generateToken(AppUser appUser) {
        return createNewToken(buildClaims(appUser));
    }

    private String createNewToken(Claims claims) {
        long newDateInMillSec =
                new Date().getTime() + jwtConfig.getTokenExpirationAfterMinutes() * MIN_TO_MILLISECONDS;
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new java.sql.Date(newDateInMillSec))
                .signWith(secretKey)
                .compact();
    }

    private Claims buildClaims(AppUser appUser) {
        Claims claims = Jwts.claims();
        claims.put(ID.getFieldName(), appUser.getId());
        claims.put(JMBG.getFieldName(), appUser.getJmbg());
        claims.put(FIRST_NAME.getFieldName(), appUser.getFirstName());
        claims.put(LAST_NAME.getFieldName(), appUser.getLastName());
        claims.put(GENDER_TYPE.getFieldName(), appUser.getGender().name());
        claims.put(EMAIL.getFieldName(), appUser.getEmail());
        claims.put(PASSWORD.getFieldName(), appUser.getPassword());
        claims.put(BLOOD_TYPE.getFieldName(), appUser.getBloodType().name());
        claims.put(IS_LOCKED.getFieldName(), appUser.getLocked());
        claims.put(IS_ENABLED.getFieldName(), appUser.getEnabled());
        claims.put(IS_BlOOD_CHECKED.getFieldName(), appUser.getIsBloodChecked());
        claims.put(APP_USER_ROLE.getFieldName(), appUser.getAppUserRole().name());
        return claims;
    }

    public String refreshToken(Claims claims){
        return createNewToken(claims);
    }


    public AppUserRole extractUserRoleFromJwt(Claims body){
        return AppUserRole.valueOf((String) body.get(APP_USER_ROLE.getFieldName()));
    }
}
