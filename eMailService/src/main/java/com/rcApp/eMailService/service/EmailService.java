package com.rcApp.eMailService.service;


import com.rcApp.eMailService.exceptions.EmailIsNotValidException;
import com.rcApp.eMailService.models.EmailMessageDTO;

public interface EmailService {
  String send(EmailMessageDTO emailMessageDTO);
  void testEmailAddress(String email) throws EmailIsNotValidException;
}
