package com.rcApp.ConfirmationTokenService.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailMessageDTO {
  private String to;
  private String subject;
  private String message;
  private Boolean toHtml;


}
