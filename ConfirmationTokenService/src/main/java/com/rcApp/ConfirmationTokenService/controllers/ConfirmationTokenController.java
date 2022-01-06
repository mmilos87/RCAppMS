package com.rcApp.ConfirmationTokenService.controllers;


import com.rcApp.ConfirmationTokenService.exception.RegistrationExceptionToken;
import com.rcApp.ConfirmationTokenService.models.ConfirmationTokenDTO;
import com.rcApp.ConfirmationTokenService.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${application.controller.base}")
@RequiredArgsConstructor
public class ConfirmationTokenController extends MainController{

  private final ConfirmationTokenService confirmationTokenService;

  @GetMapping(path ="${application.controller.endpoints.confirm}")
  public String confirmToken(@RequestParam("token")String token)
      throws RegistrationExceptionToken {
    return confirmationTokenService.confirmToken(token);
  }

  @PostMapping(path ="${application.controller.endpoints.save}")
  public String createAndSendEmail(@RequestBody ConfirmationTokenDTO tokenDTO) {

    return confirmationTokenService.createConfirmationTokenAndSendConfirmationEmail(tokenDTO);
  }
  //ponovno slanje emaila za potvrdu javani end point
  @PostMapping(value = "sendNewConfirmationToken")
  public String sendNewConfirmationToken(@RequestBody ConfirmationTokenDTO confirmationTokenDTO){
    return confirmationTokenService.sendNewConfirmationToken(confirmationTokenDTO);
  }

}
