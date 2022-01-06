package com.rcApp.notificationService.models;

import com.rcApp.notificationService.entitety.AppUser;
import com.rcApp.notificationService.entitety.UserCity;
import com.rcApp.notificationService.helpers.enums.BloodTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodRequestDTO {

    private List<BloodTypes> compatibleBloodTypes;
    private UserCity userCity;
    private LocalDateTime bloodCondition;
    private LocalDateTime plateletsCondition;
    private LocalDateTime bloodPlasmaCondition;
    private AppUser recipient;
    private int donorToNotify;

}
