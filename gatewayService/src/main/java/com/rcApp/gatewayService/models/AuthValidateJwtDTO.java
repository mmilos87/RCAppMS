package com.rcApp.gatewayService.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthValidateJwtDTO {
    private String authToken;
    private List<String> allowedRoles;
}
