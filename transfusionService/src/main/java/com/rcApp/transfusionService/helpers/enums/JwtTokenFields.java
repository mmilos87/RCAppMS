package com.rcApp.transfusionService.helpers.enums;


public enum JwtTokenFields {
  ID("id"),
  JMBG("jmbg"),
  FIRST_NAME("firstName"),
  LAST_NAME("lastName"),
  GENDER_TYPE("gender"),
  EMAIL("email"),
  PASSWORD("password"),
  BLOOD_TYPE("bloodType"),
  IS_LOCKED("isLocked"),
  IS_ENABLED("isEnabled"),
  IS_BlOOD_CHECKED("isBloodChecked"),
  APP_USER_ROLE("appUserRole");


  private String fieldName;

  public String getFieldName() {
    return fieldName;
  }

  JwtTokenFields(String fieldName) {
    this.fieldName = fieldName;
  }
}








