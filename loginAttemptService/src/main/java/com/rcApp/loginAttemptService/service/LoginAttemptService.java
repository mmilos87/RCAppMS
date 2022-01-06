package com.rcApp.loginAttemptService.service;

import com.rcApp.loginAttemptService.models.AppUserDTO;

public interface LoginAttemptService {
    void failureLoginAttempt(AppUserDTO appUserDTO);
    void successLoginAttempt(AppUserDTO appUserDTO);
}
