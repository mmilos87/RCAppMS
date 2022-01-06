package com.rcApp.loginAttemptService.controler;

import com.rcApp.loginAttemptService.models.AppUserDTO;
import com.rcApp.loginAttemptService.service.LoginAttemptService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class LoginAttemptController {

    private LoginAttemptService attemptService;

    @PostMapping("success")
    public void successLoginAttempt(@RequestBody AppUserDTO appUserDTO){

      attemptService.successLoginAttempt(appUserDTO);
    }

    @PostMapping("failure")
   public void failureLoginAttempt(@RequestBody AppUserDTO appUserDTO){

     attemptService.failureLoginAttempt(appUserDTO);
    }
}
