package com.rcApp.ConfirmationTokenService.controllers;
import com.rcApp.ConfirmationTokenService.exception.RegistrationException;
import com.rcApp.ConfirmationTokenService.models.ExceptionDTO;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class MainController {

  @ExceptionHandler(RegistrationException.class)
  public ResponseEntity<ExceptionDTO> handlerRegE(RegistrationException exception) {
    String errorMessage = exception.getMessage();
    return new ResponseEntity<>(new ExceptionDTO(errorMessage), HttpStatus.BAD_REQUEST);
  }
}
