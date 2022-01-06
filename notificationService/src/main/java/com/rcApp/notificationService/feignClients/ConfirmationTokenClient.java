package com.rcApp.notificationService.feignClients;


import com.rcApp.notificationService.models.ConfirmationTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="confirmationTokenService")
public interface ConfirmationTokenClient {
    @PostMapping("api/v1/save")
    String saveAndSendEmail(@RequestBody ConfirmationTokenDTO tokenDTO);
}
