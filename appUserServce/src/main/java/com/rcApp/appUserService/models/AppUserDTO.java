package com.rcApp.appUserService.models;

import com.rcApp.appUserService.helpers.enums.AppUserRole;
import com.rcApp.appUserService.helpers.enums.BloodTypes;
import com.rcApp.appUserService.helpers.enums.GenderType;
import com.rcApp.appUserService.helpers.enums.RCLoginAuthProvider;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDTO   {

    private Long id;

    private Long jmbg;

    private String firstName;

    private String lastName;

    private GenderType gender;

    private String email;

    private String password;

    private BloodTypes bloodType;

    private AppUserRole appUserRole;

    private Boolean locked  ;

    private Boolean enabled  ;

    private Boolean isBloodChecked ;

    private RCLoginAuthProvider authProvider ;

}
