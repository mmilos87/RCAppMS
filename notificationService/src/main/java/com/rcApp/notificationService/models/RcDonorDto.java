package com.rcApp.notificationService.models;

import com.rcApp.notificationService.entitety.AppUser;
import com.rcApp.notificationService.entitety.RcAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RcDonorDto {

    private Long id;
    private AppUser appUser;
    private LocalDateTime dateLastBloodGiving;
    private Long numberOfBloodGiving;
    private LocalDateTime dateLastPlateletsGiving;
    private Long numberOfPlateletsGiving;
    private LocalDateTime dateLastBloodPlasmaGiving;
    private Long numberOfBloodPlasmaGiving;
    private Boolean hasBeenRejected;
    private Boolean sentNotification;
    private Boolean confirmNotification;
    private RcAddress address;
}
