package com.rcApp.appUserService.service;

import com.rcApp.appUserService.entitety.AppUser;
import com.rcApp.appUserService.exception.EmailIsNotValidException;
import com.rcApp.appUserService.feignClients.ConfirmationTokenClient;
import com.rcApp.appUserService.helpers.enums.AppMessages;
import com.rcApp.appUserService.helpers.enums.ConfirmationTokenType;
import com.rcApp.appUserService.helpers.mappers.AppUserMapper;
import com.rcApp.appUserService.models.AppUserDTO;
import com.rcApp.appUserService.models.ConfirmationTokenDTO;
import com.rcApp.appUserService.repo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenClient confirmationTokenClient;
    private final static String ACTIVATION_MSG = "Please click on the below link to activate your account: ";

    @Override
    public AppUserDTO saveAppUser(AppUserDTO appUserDTO) throws EmailIsNotValidException {
        if (isExist(appUserDTO.getEmail())) {
            throw new EmailIsNotValidException(AppMessages.EMAIL_IS_ALREADY_TAKEN);
        }
        String encodePassword = bCryptPasswordEncoder.encode(appUserDTO.getPassword());
        appUserDTO.setPassword(encodePassword);
        AppUser appUser = appUserRepository.saveAndFlush(AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO));
        AppUserDTO savedAppUserDTO = AppUserMapper.INSTANCE.appUserToDto(appUser);
        String msg = "Thank you for registering. " + ACTIVATION_MSG;
        ConfirmationTokenDTO tokenDTO =
                new ConfirmationTokenDTO(savedAppUserDTO,
                        ConfirmationTokenType.USER_CONFIRMATION, savedAppUserDTO.getId(), msg);
        String token = confirmationTokenClient.saveAndSendEmail(tokenDTO);
        return savedAppUserDTO;
    }

    @Override
    public String enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
        return "enabled user: " + email;
    }

    @Override
    public boolean isExist(String email) {
        return appUserRepository.findByEmail(email).isPresent();
    }

    @Override
    public AppUserDTO saveOauthUser(AppUserDTO appUserDTO) {
        AppUser appUser = AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO);
        AppUser save = appUserRepository.save(appUser);
        AppUserDTO appUserDTO1 = AppUserMapper.INSTANCE.appUserToDto(save);
        return appUserDTO1;
    }

    @Override
    public AppUserDTO updateUser(Long id, AppUserDTO appUserDTO) {
        appUserRepository.findById(id).ifPresentOrElse(appUser -> {
            appUser = AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO);
            appUser.setId(id);
            appUserRepository.save(appUser);
        }, () -> new IllegalStateException("user not exist"));
        return appUserDTO;
    }

    @Override
    public Long deleteUser(Long id) {

        appUserRepository.findById(id).ifPresentOrElse(appUser -> {
            appUserRepository.delete(appUser);
        }, () -> new IllegalStateException("user not exist"));
        return id;
    }

    @Override
    public AppUserDTO getUserById(Long id) {
        AppUser appUser= appUserRepository.findById(id).orElseThrow(
        () -> new IllegalStateException("user not exist"));
        return AppUserMapper.INSTANCE.appUserToDto(appUser);
    }

    @Override
    public AppUserDTO loadUserByJMBG(Long jMbg) {
        AppUser userByJMBG = appUserRepository.findByJmbg(jMbg);
        AppUserDTO appUserDTO = AppUserMapper.INSTANCE.appUserToDto(userByJMBG);
        return appUserDTO;
    }

    @Override
    public String sendNewToken(String email) {
        AppUserDTO appUserDTO = loadUserByUsername(email);
        ConfirmationTokenDTO tokenDTO =
                new ConfirmationTokenDTO(appUserDTO,
                        ConfirmationTokenType.USER_CONFIRMATION,
                        appUserDTO.getId(), ACTIVATION_MSG);
        String token = confirmationTokenClient.sendNewConfirmationToken(tokenDTO);
        return token;
    }


    @Override
    public AppUserDTO loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(AppMessages.USER_WITH_THAT_EMAIL_NOT_FOUND.getMessage(), email)));
        AppUserDTO appUserDTO = AppUserMapper.INSTANCE.appUserToDto(appUser);
        return appUserDTO;
    }
}
