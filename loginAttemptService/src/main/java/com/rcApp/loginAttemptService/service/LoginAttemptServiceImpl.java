package com.rcApp.loginAttemptService.service;

import com.rcApp.loginAttemptService.entitety.AppUser;
import com.rcApp.loginAttemptService.entitety.LoginAttempts;
import com.rcApp.loginAttemptService.helpers.mappers.AppUserMapper;
import com.rcApp.loginAttemptService.models.AppUserDTO;
import com.rcApp.loginAttemptService.repo.LoginAttemptRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LoginAttemptServiceImpl implements LoginAttemptService {
    private final LoginAttemptRepo attemptsRepo;
    private static final int MAX_FAILURE_ATTEMPTS = 3;

    @Override
    public void failureLoginAttempt(AppUserDTO appUserDTO) {
        AppUser appUser = AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO);
        attemptsRepo
                .findByAppUser(appUser)
                .ifPresentOrElse(
                        loginAttempts -> {
                            if (loginAttempts.getNumberOfFailureAttempts() < MAX_FAILURE_ATTEMPTS)
                                loginAttempts.increaseNumberOfFailureAttempts();
                            if (loginAttempts.getNumberOfFailureAttempts() == MAX_FAILURE_ATTEMPTS)
                                //todo disable user or send notification email
                                loginAttempts.setDateOfLastAttempt(LocalDateTime.now());
                            attemptsRepo.save(loginAttempts);
                        },
                        () ->
                                attemptsRepo.save(
                                        LoginAttempts.builder()
                                                .appUser(appUser)
                                                .numberOfFailureAttempts(1)
                                                .dateOfLastAttempt(LocalDateTime.now())
                                                .build()));

    }

    @Override
    public void successLoginAttempt(AppUserDTO appUserDTO) {
        AppUser appUser = AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO);
        attemptsRepo
                .findByAppUser(appUser)
                .ifPresentOrElse(
                        loginAttempts -> {
                            loginAttempts.resetNumberOfFailureAttempts();
                            attemptsRepo.save(loginAttempts);
                        },
                        () ->
                                attemptsRepo.save(
                                        LoginAttempts.builder()
                                                .appUser(appUser)
                                                .dateOfLastAttempt(LocalDateTime.now())
                                                .build()));
    }
}
