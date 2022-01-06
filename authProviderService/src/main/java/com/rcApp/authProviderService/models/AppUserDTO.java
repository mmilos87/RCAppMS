package com.rcApp.authProviderService.models;

import com.rcApp.authProviderService.helpers.enums.AppUserRole;
import com.rcApp.authProviderService.helpers.enums.BloodTypes;
import com.rcApp.authProviderService.helpers.enums.GenderType;
import com.rcApp.authProviderService.helpers.enums.RCLoginAuthProvider;
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
