package com.rcApp.appUserService.service;


import com.rcApp.appUserService.exception.EmailIsNotValidException;
import com.rcApp.appUserService.models.AppUserDTO;


public interface AppUserService {

    AppUserDTO saveAppUser(AppUserDTO appUserDTO) throws EmailIsNotValidException;

    AppUserDTO updateUser(Long id, AppUserDTO appUserDTO);

    Long deleteUser(Long id);

    AppUserDTO getUserById(Long id);

    AppUserDTO loadUserByUsername(String email);

    String enableAppUser(String email);

    boolean isExist(String email);

    AppUserDTO saveOauthUser(AppUserDTO appUserDTO);

    AppUserDTO loadUserByJMBG(Long jMbg);

    String sendNewToken(String email);
}
