package com.rcApp.authProviderService.controler;

import com.rcApp.authProviderService.exception.DeviceAndLocationException;
import com.rcApp.authProviderService.models.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AuthProviderServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionDTO> handleState(IllegalStateException exception) {
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(new ExceptionDTO(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handlerUsr(UsernameNotFoundException exception) {
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(new ExceptionDTO(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeviceAndLocationException.class)
    public ResponseEntity<ExceptionDTO> handlerDevice(DeviceAndLocationException exception) {
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(new ExceptionDTO(errorMessage), HttpStatus.BAD_REQUEST);
    }
}
