package com.rcApp.appUserService.exception;


import com.rcApp.appUserService.helpers.enums.AppMessages;

public class JmbgIsNotValidException extends Exception {
  private final AppMessages appMessages;

  public JmbgIsNotValidException(AppMessages appMessages) {
    super(appMessages.getMessage());
    this.appMessages = appMessages;
  }

  public AppMessages getAppMessages() {
    return appMessages;
  }

}
