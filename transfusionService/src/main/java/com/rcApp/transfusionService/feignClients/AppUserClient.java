package com.rcApp.transfusionService.feignClients;

import com.rcApp.transfusionService.models.AppUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="appUserService",fallbackFactory = AppUserClientFallbackFactory.class)
public interface AppUserClient {

    @RequestMapping(method = RequestMethod.POST, value = "api/v1/enable", consumes = "application/json")
    String enableUser(@RequestBody String email);

    @PostMapping(value = "api/v1/getUserByEmail")
    AppUserDTO loadUserByEmail(@RequestBody String email)throws UsernameNotFoundException;

    @PostMapping(value = "api/v1/saveOauthUser")
    AppUserDTO saveOauthUser(@RequestBody AppUserDTO appUserDTO);

    @PostMapping(value = "api/v1/isExist")
    boolean isExist(@RequestBody String email);

    @PostMapping(value = "api/v1/getUserByJmbg")
    AppUserDTO loadUserByJmbg(@RequestBody Long jmbg);
}
