package com.rcApp.ConfirmationTokenService.feignClients;

import com.rcApp.ConfirmationTokenService.models.RcConfirmDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("deviceAndLocationService")
public interface DeviceAndLocationClient {
    @PostMapping("/api/v1/enable")
    String enableDevice(@RequestBody RcConfirmDto appUserDTO);

}
