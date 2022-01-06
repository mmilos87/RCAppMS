package com.rcApp.authProviderService.feignClients;

import com.rcApp.authProviderService.models.AppUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("loginAttemptService")
public interface LoginAttemptClient {
    @PostMapping("/api/v1/success")
    public void successLoginAttempt(@RequestBody AppUserDTO appUserDTO);

    @PostMapping("/ap1/v1/failure")
    public void failureLoginAttempt(@RequestBody AppUserDTO appUserDTO);
}
