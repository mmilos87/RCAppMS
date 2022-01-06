package com.rcApp.rcTransfusionQueryService;

import com.rcApp.rcTransfusionQueryService.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class RcTransfusionQueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RcTransfusionQueryServiceApplication.class, args);
	}

}
