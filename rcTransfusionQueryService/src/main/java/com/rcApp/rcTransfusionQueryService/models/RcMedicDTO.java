package com.rcApp.rcTransfusionQueryService.models;

import com.rcApp.rcTransfusionQueryService.entitety.AppUser;
import com.rcApp.rcTransfusionQueryService.entitety.HospitalUnit;
import com.rcApp.rcTransfusionQueryService.helpers.enums.MedicTitle;
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
