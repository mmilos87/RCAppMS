package com.rcApp.rcTransfusionQueryService.models;

import com.rcApp.rcTransfusionQueryService.helpers.enums.AppUserRole;
import com.rcApp.rcTransfusionQueryService.helpers.enums.BloodTypes;
import com.rcApp.rcTransfusionQueryService.helpers.enums.GenderType;
import com.rcApp.rcTransfusionQueryService.helpers.enums.RCLoginAuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDTO {

    private Long id;
    private Long jmbg;

    private String firstName;

    private String lastName;

    private GenderType gender;

    private String email;

    private String password;

    private BloodTypes bloodType;

    private AppUserRole appUserRole;

    private Boolean locked ;

    private Boolean enabled  ;

    private Boolean isBloodChecked ;

    private RCLoginAuthProvider authProvider ;

}
