package com.rcApp.notificationService.controlers;

import com.rcApp.notificationService.models.RcConfirmDto;
import com.rcApp.notificationService.models.TransfusionQueryDTO;
import com.rcApp.notificationService.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class NotificationController extends MainController {
    private final NotificationService notificationService;

    @PostMapping("initial")
    public void initialNotifications(@RequestBody TransfusionQueryDTO transfusionQueryDTO){
      notificationService.initialNotifications(transfusionQueryDTO);
    }

    @PostMapping("confTransfusion")
    public String confTransfusion(@RequestBody RcConfirmDto rcConfirmDto){
        return notificationService.donorConfNotification(rcConfirmDto.getId());
    }
}
