package com.rcApp.notificationService.models;

import com.rcApp.notificationService.helpers.enums.*;
import lombok.Getter;

@Getter
public class RequestByType {

  private TransfusionTypes transfusionType;
  private BloodTypes bloodType;
  private GenderType genderType;
  private MedicTitle medicTitle;
  private AppUserRole role;
}
