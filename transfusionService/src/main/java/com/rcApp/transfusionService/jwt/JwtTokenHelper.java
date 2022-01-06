package com.rcApp.transfusionService.jwt;

import com.rcApp.transfusionService.entitety.AppUser;
import com.rcApp.transfusionService.helpers.enums.AppUserRole;
import com.rcApp.transfusionService.helpers.enums.BloodTypes;
import com.rcApp.transfusionService.helpers.enums.GenderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import static com.rcApp.transfusionService.helpers.enums.JwtTokenFields.*;

@AllArgsConstructor
@Component
public class JwtTokenHelper {

  private final JwtConfig jwtConfig;
  private final SecretKey secretKey;

  public AppUser extractAppUserFromJwtClaims(HttpServletRequest request) {
    String authHeader = request.getHeader(jwtConfig.getAuthorizationHeaders());
    String token = authHeader.replace(jwtConfig.getTokenPrefix(), "");
    Claims body = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
    return  AppUser.builder()
        .jmbg(body.get(JMBG.getFieldName(), Long.class))
        .firstName(body.get(FIRST_NAME.getFieldName(), String.class))
        .lastName(body.get(LAST_NAME.getFieldName(), String.class))
        .gender(GenderType.valueOf((String) body.get(GENDER_TYPE.getFieldName())))
        .email(body.get(EMAIL.getFieldName(), String.class))
        .password(body.get(PASSWORD.getFieldName(), String.class))
        .bloodType(BloodTypes.valueOf((String) body.get(BLOOD_TYPE.getFieldName())))
        .locked(body.get(IS_LOCKED.getFieldName(), Boolean.class))
        .enabled(body.get(IS_ENABLED.getFieldName(), Boolean.class))
        .isBloodChecked(body.get(IS_BlOOD_CHECKED.getFieldName(), Boolean.class))
        .appUserRole(AppUserRole.valueOf((String) body.get(APP_USER_ROLE.getFieldName())))
        .build();
  }

}
