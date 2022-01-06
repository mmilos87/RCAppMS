package com.rcApp.eMailService.exceptions;


import com.rcApp.eMailService.helpers.AppMessages;

public class EmailIsNotValidException extends Exception{
  private final AppMessages appMessages;

  public EmailIsNotValidException(AppMessages appMessages) {
    super(appMessages.getMessage());
    this.appMessages = appMessages;
  }

  public AppMessages getAppMessages() {
    return appMessages;
  }
}
