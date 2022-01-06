package com.rcApp.transfusionService;

import com.rcApp.transfusionService.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class TransfusionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransfusionServiceApplication.class, args);
	}

}
