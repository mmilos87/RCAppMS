package com.rcApp.ConfirmationTokenService.feignClients;


import com.rcApp.ConfirmationTokenService.models.EmailMessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("emailService")
public interface EmailClient {

   @PostMapping("api/v1/sendEmail")
   String sendEmail(@RequestBody EmailMessageDTO emailMessageDTO);
}
