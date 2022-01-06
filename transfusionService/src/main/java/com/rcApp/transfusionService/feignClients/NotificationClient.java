package com.rcApp.transfusionService.feignClients;

import com.rcApp.transfusionService.models.TransfusionQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notificationService")
public interface NotificationClient {
    @PostMapping("/api/v1/initial")
    void initialNotifications(@RequestBody TransfusionQueryDTO transfusionQueryDTO);
}
