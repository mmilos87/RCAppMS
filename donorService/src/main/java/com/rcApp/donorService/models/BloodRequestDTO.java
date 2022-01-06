package com.rcApp.donorService.models;

import com.rcApp.donorService.entitety.AppUser;
import com.rcApp.donorService.entitety.UserCity;
import com.rcApp.donorService.helpers.enums.BloodTypes;
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
