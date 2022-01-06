package com.rcApp.appUserService.models;

import com.rcApp.appUserService.exception.JmbgIsNotValidException;
import com.rcApp.appUserService.helpers.classes.UniqueMasterCitizenNumberHelper;
import com.rcApp.appUserService.helpers.enums.AppUserRole;
import com.rcApp.appUserService.helpers.enums.BloodTypes;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequestAppUser {
  private final Long jmbg;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String password;
  private BloodTypes bloodType;

  public AppUserDTO getAppUserDTO() throws JmbgIsNotValidException {
    return AppUserDTO.builder()
        .gender(new UniqueMasterCitizenNumberHelper(jmbg.toString()).getGender())
        .firstName(firstName)
        .bloodType(bloodType)
        .password(password)
        .appUserRole(AppUserRole.USER)
        .lastName(lastName)
        .email(email)
        .jmbg(jmbg)
        .build();
  }

}
