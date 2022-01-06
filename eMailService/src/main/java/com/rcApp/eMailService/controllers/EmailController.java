package com.rcApp.eMailService.controllers;


import com.rcApp.eMailService.models.EmailMessageDTO;
import com.rcApp.eMailService.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class EmailController extends MainController{

  private final EmailService emailService;

  @PostMapping(path = "sendEmail")
  public String sendEmail(@RequestBody EmailMessageDTO emailMessageDTO) {

     return emailService.send(emailMessageDTO);
  }

}
