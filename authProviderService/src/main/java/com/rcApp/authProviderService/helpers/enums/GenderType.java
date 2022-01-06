package com.rcApp.authProviderService.helpers.enums;

public enum GenderType {
  MALE("Male"),
  FEMALE("Female"),
  NOT_CHECKED("Not checked");
  private final String gender;

  GenderType(String gender) {
    this.gender = gender;
  }
}
