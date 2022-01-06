package com.rcApp.ConfirmationTokenService.feignClients;

import com.rcApp.ConfirmationTokenService.models.RcConfirmDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notificationService")
public interface NotificationClient {
    @PostMapping("/api/v1/confTransfusion")
    String confTransfusion(@RequestBody RcConfirmDto rcConfirmDto);
}
