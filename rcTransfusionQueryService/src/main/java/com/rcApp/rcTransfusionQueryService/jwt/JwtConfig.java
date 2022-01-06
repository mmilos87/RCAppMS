package com.rcApp.rcTransfusionQueryService.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Long tokenExpirationAfterMinutes;



    public String getAuthorizationHeaders(){
        return HttpHeaders.AUTHORIZATION;
    }
}
