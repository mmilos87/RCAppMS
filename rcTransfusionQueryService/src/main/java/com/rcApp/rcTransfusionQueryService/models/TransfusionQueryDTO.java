package com.rcApp.rcTransfusionQueryService.models;

import com.rcApp.rcTransfusionQueryService.entitety.AppUser;
import com.rcApp.rcTransfusionQueryService.entitety.HospitalUnit;
import com.rcApp.rcTransfusionQueryService.entitety.RcUserMedic;
import com.rcApp.rcTransfusionQueryService.helpers.enums.TransfusionTypes;
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
