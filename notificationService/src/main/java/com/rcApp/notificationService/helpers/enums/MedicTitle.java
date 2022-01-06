package com.rcApp.notificationService.helpers.enums;

public enum MedicTitle {
  DOCTOR("doctor"),
  NURSE("nurse"),
  ADMINISTRATOR("administrator"),
  ASSISTANT("assistant");
  private final String title;

  MedicTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
