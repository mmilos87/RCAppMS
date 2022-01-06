package com.rcApp.notificationService.models;

import com.rcApp.notificationService.entitety.AppUser;
import com.rcApp.notificationService.entitety.HospitalUnit;
import com.rcApp.notificationService.helpers.enums.MedicTitle;
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
