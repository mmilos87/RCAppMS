package com.rcApp.appUserService.feignClients;

import com.rcApp.appUserService.models.ConfirmationTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name="confirmationTokenService")
public interface ConfirmationTokenClient {
    @PostMapping("/api/v1/save")
    String saveAndSendEmail(@RequestBody ConfirmationTokenDTO tokenDTO);

    @PostMapping("/api/v1/sendNewConfirmationToken")
    String sendNewConfirmationToken(@RequestBody ConfirmationTokenDTO confirmationTokenDTO);



}
