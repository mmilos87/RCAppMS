package com.rcApp.ConfirmationTokenService.exception;


import com.rcApp.ConfirmationTokenService.enums.AppMessages;

public class RegistrationExceptionToken extends RegistrationException{

  public RegistrationExceptionToken( AppMessages appMessages) {
    super(appMessages);
  }

}
