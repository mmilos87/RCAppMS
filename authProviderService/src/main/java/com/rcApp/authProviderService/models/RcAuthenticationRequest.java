package com.rcApp.authProviderService.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RcAuthenticationRequest {
   private String username;
   private  String password;
}
