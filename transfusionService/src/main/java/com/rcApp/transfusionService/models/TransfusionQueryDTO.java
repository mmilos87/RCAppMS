package com.rcApp.transfusionService.models;

import com.rcApp.transfusionService.entitety.AppUser;
import com.rcApp.transfusionService.entitety.HospitalUnit;
import com.rcApp.transfusionService.entitety.RcUserMedic;
import com.rcApp.transfusionService.helpers.enums.TransfusionTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransfusionQueryDTO {

    private Long id;
    private AppUser recipient;
    private HospitalUnit hospitalUnit;
    private RcUserMedic rcUserMedic;
    private TransfusionTypes transfusionType;
    private Long requiredUnits;
    private LocalDateTime createdAt;
    private Boolean onlyRecipientBloodType;
    private Long numberOfNotifiedDonors;
    private Long numberOfConfirmedDonors;

}
