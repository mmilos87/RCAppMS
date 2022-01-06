package com.rcApp.appUserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class AppUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppUserServiceApplication.class, args);
	}

}
