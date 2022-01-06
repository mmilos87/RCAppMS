package com.rcApp.eMailService.service;

import com.rcApp.eMailService.exceptions.EmailIsNotValidException;
import com.rcApp.eMailService.helpers.AppMessages;
import com.rcApp.eMailService.models.EmailMessageDTO;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Log
public class EmailServiceImpl implements  EmailService {

  private final JavaMailSender javaMailSender;
  @Override
  public String send(EmailMessageDTO emailMessageDTO) {
    try{
      MimeMessage mimeMessage= javaMailSender.createMimeMessage();
      MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,"utf-8");
      helper.setText(emailMessageDTO.getMessage(), emailMessageDTO.getToHtml());
      helper.setTo(emailMessageDTO.getTo());
      helper.setSubject(emailMessageDTO.getSubject());
       javaMailSender.send(mimeMessage);
    }
    catch(MessagingException e){
      log.info("porika nije poslata");
    }
    return "email sent";
  }

  @Override
  public void testEmailAddress(String email) throws EmailIsNotValidException {
    if (!isValid(email)) {
      throw new EmailIsNotValidException(AppMessages.EMAIL_IS_NOT_VALID);
    }
  }

  private boolean isValid(String email) {
    String emailRegex =
        "^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
            + "A-Z]{2,7}$";
    Pattern pat = Pattern.compile(emailRegex);

    return pat.matcher(email).matches();
  }
}
