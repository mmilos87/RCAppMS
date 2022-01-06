package com.rcApp.authProviderService.services;

import com.rcApp.authProviderService.exception.DeviceAndLocationException;
import com.rcApp.authProviderService.models.AppUserDTO;
import com.rcApp.authProviderService.models.AuthValidateJwtDTO;
import com.rcApp.authProviderService.models.RcAuthenticationRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface AuthProviderService {
    AppUserDTO login(RcAuthenticationRequest rcAuthenticationRequest, HttpServletRequest request)
            throws IOException, DeviceAndLocationException;
    String verifyAndRefreshJwt(AuthValidateJwtDTO jwtForVerify);
}
