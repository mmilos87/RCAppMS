package com.rcApp.loginAttemptService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoginAttemptServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginAttemptServiceApplication.class, args);
	}

}
