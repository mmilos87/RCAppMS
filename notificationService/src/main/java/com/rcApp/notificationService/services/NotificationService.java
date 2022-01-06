package com.rcApp.notificationService.services;


import com.rcApp.notificationService.models.TransfusionQueryDTO;

public interface NotificationService {
    void initialNotifications(TransfusionQueryDTO transfusionQueryDTO);


    String donorConfNotification(Long id);
}
