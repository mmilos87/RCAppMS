package com.rcApp.ConfirmationTokenService.models;

import com.rcApp.ConfirmationTokenService.enums.AppUserRole;
import com.rcApp.ConfirmationTokenService.enums.BloodTypes;
import com.rcApp.ConfirmationTokenService.enums.GenderType;
import com.rcApp.ConfirmationTokenService.enums.RCLoginAuthProvider;
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

    private Boolean locked ;

    private Boolean enabled  ;

    private Boolean isBloodChecked ;

    private RCLoginAuthProvider authProvider ;

}
