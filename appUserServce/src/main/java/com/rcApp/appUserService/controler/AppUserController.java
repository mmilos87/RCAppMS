package com.rcApp.appUserService.controler;

import com.rcApp.appUserService.exception.EmailIsNotValidException;
import com.rcApp.appUserService.exception.JmbgIsNotValidException;
import com.rcApp.appUserService.models.AppUserDTO;
import com.rcApp.appUserService.models.RegistrationRequestAppUser;
import com.rcApp.appUserService.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class AppUserController extends MainController {

    private final AppUserService appUserService;

    @PostMapping(value = "appUser")
    public AppUserDTO create(@RequestBody RegistrationRequestAppUser registrationRequestAppUser,
                             HttpServletRequest httpServletRequest) throws JmbgIsNotValidException, EmailIsNotValidException {
        return appUserService.saveAppUser(registrationRequestAppUser.getAppUserDTO());
    }

    @PutMapping(value = "appUser/{id}")
    public AppUserDTO update(@PathVariable("id") Long id, @RequestBody AppUserDTO appUserDTO) {

        return appUserService.updateUser(id, appUserDTO);
    }

    @DeleteMapping(value = "appUser/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return appUserService.deleteUser(id);
    }

    @GetMapping(value= "appUser/{id}")
    public AppUserDTO get(@PathVariable("id") Long id) {

        return appUserService.getUserById(id);
    }

    @PostMapping(value = "enable")
    public String enableUser(@RequestBody String email) {
        return appUserService.enableAppUser(email);
    }

    @PostMapping(value = "getUserByEmail")
    public AppUserDTO loadUserByEmail(@RequestBody String email) throws UsernameNotFoundException {
        return appUserService.loadUserByUsername(email);
    }

    @PostMapping(value = "saveOauthUser")
    public AppUserDTO saveOauthUser(@RequestBody AppUserDTO appUserDTO) {
        return appUserService.saveOauthUser(appUserDTO);
    }

    @PostMapping(value = "isExist")
    public boolean isExist(@RequestBody String email) {
        return appUserService.isExist(email);
    }


    @PostMapping(value = "getUserByJmbg")
    public AppUserDTO loadUserByJMBG(@RequestBody Long jMbg) {
        return appUserService.loadUserByJMBG(jMbg);
    }


    @PostMapping("newConfToken")
    public String sendNevConfToken(@RequestBody AppUserDTO appUserDTO) {
        return appUserService.sendNewToken(appUserDTO.getEmail());
    }
    //todo implementirati ostle crud metode

}
