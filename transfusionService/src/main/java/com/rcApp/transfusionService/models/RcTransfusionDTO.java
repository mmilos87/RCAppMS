package com.rcApp.transfusionService.models;

import com.rcApp.transfusionService.entitety.HospitalUnit;
import com.rcApp.transfusionService.entitety.RcUserDonor;
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
public class RcTransfusionDTO {

    private Long id;
    private LocalDateTime date;
    private TransfusionTypes type;
    private RcUserDonor donor;
    private HospitalUnit hospitalUnit;
    private RcUserMedic rcUserMedic;
    private Boolean isDedicated;
}
