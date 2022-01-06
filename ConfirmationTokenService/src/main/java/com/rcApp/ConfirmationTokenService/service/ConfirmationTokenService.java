package com.rcApp.ConfirmationTokenService.service;

import com.rcApp.ConfirmationTokenService.enums.ConfirmationTokenType;
import com.rcApp.ConfirmationTokenService.exception.RegistrationExceptionToken;
import com.rcApp.ConfirmationTokenService.models.ConfirmationTokenDTO;


public interface ConfirmationTokenService {

 String createConfirmationTokenAndSendConfirmationEmail(ConfirmationTokenDTO tokenDTO);

   String confirmToken(String token) throws RegistrationExceptionToken;

    String sendNewConfirmationToken(ConfirmationTokenDTO tokenDTO);
}
