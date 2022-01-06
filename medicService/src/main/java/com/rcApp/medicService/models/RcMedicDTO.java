package com.rcApp.medicService.models;

import com.rcApp.medicService.entitety.AppUser;
import com.rcApp.medicService.entitety.HospitalUnit;
import com.rcApp.medicService.helpers.enums.MedicTitle;
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
