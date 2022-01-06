package com.rcApp.transfusionService.models;

import com.rcApp.transfusionService.entitety.AppUser;
import com.rcApp.transfusionService.entitety.HospitalUnit;
import com.rcApp.transfusionService.helpers.enums.MedicTitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RcMedicDTO {

    private Long id;

    private AppUser appUser;

    private MedicTitle title;

    private HospitalUnit hospitalUnit;

}
