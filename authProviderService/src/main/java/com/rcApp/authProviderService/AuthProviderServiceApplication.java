package com.rcApp.authProviderService;

import com.rcApp.authProviderService.security.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableConfigurationProperties(JwtConfig.class)
public class AuthProviderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthProviderServiceApplication.class, args);
	}

}
