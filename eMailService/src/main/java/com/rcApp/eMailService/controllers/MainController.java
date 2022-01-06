package com.rcApp.eMailService.controllers;
import com.rcApp.eMailService.exceptions.EmailIsNotValidException;
import com.rcApp.eMailService.models.ExceptionDTO;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class MainController {

  @ExceptionHandler(EmailIsNotValidException.class)
  public ResponseEntity<ExceptionDTO> handlerEmail(EmailIsNotValidException exception) {
    String errorMessage = exception.getMessage();
    return new ResponseEntity<>(new ExceptionDTO(errorMessage), HttpStatus.UNAUTHORIZED);
  }

}
