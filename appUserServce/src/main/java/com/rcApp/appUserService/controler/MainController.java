package com.rcApp.appUserService.controler;
import com.rcApp.appUserService.exception.EmailIsNotValidException;
import com.rcApp.appUserService.exception.JmbgIsNotValidException;
import com.rcApp.appUserService.models.ExceptionDTO;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

  @ExceptionHandler(JmbgIsNotValidException.class)
  public ResponseEntity<ExceptionDTO> handlerJmbg(JmbgIsNotValidException exception) {
    String errorMessage = exception.getMessage();
    return new ResponseEntity<>(new ExceptionDTO(errorMessage), HttpStatus.BAD_REQUEST);
  }
//  @ExceptionHandler(UsernameNotFoundException.class)
//  public ResponseEntity<ExceptionDTO> handlerUsr(UsernameNotFoundException exception) {
//    String errorMessage = exception.getMessage();
//    return new ResponseEntity<>(new ExceptionDTO(errorMessage), HttpStatus.BAD_REQUEST);
//  }

}
