package com.rcApp.ConfirmationTokenService.models;

import com.rcApp.ConfirmationTokenService.enums.ConfirmationTokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationTokenDTO {
    private AppUserDTO appUserDTO;
    private ConfirmationTokenType tokenType;
    private Long confirmationTypeId;
    private String message;
}
