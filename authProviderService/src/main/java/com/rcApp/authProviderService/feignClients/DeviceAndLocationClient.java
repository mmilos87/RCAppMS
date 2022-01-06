package com.rcApp.authProviderService.feignClients;

import com.rcApp.authProviderService.helpers.enums.AppMessages;
import com.rcApp.authProviderService.models.DeviceAndLocationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@FeignClient("deviceAndLocationService")
public interface DeviceAndLocationClient {

    @PostMapping("/api/v1/verify")
    AppMessages verifyDevice(@RequestBody DeviceAndLocationDTO deviceAndLocationDTO)throws IOException;

}
