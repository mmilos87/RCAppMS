package com.rcApp.rcTransfusionQueryService.feignClients;

import com.rcApp.rcTransfusionQueryService.models.TransfusionQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notificationService")
public interface NotificationClient {
    @PostMapping("/api/v1/initial")
    void initialNotifications(@RequestBody TransfusionQueryDTO transfusionQueryDTO);
}
