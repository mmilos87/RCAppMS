package com.rcApp.notificationService.controlers;

import com.rcApp.notificationService.models.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionDTO> handlerRegE(IllegalStateException exception) {
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(new ExceptionDTO(errorMessage), HttpStatus.BAD_REQUEST);
    }
}
