package com.rcApp.deviceAndLocationService.controler;
import com.rcApp.deviceAndLocationService.exception.DeviceAndLocationException;
import com.rcApp.deviceAndLocationService.models.ExceptionDTO;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class MainController {

  @ExceptionHandler(DeviceAndLocationException.class)
  public ResponseEntity<ExceptionDTO> handlerRegE(DeviceAndLocationException exception) {
    String errorMessage = exception.getMessage();
    return new ResponseEntity<>(new ExceptionDTO(errorMessage), HttpStatus.BAD_REQUEST);
  }
}
