package com.rcApp.rcTransfusionQueryService.feignClients;


import com.rcApp.rcTransfusionQueryService.models.AppUserDTO;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AppUserClientFallbackFactory implements FallbackFactory<AppUserClient> {

    @Override
    public AppUserClient create(Throwable cause) {
        String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";

        return new AppUserClient() {
            @Override
            public String enableUser(String email) {
                return null;
            }

            @Override
            public AppUserDTO loadUserByEmail(String email)throws UsernameNotFoundException {
                log.debug("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"+cause);
                return null;
            }

            @Override
            public AppUserDTO saveOauthUser(AppUserDTO appUserDTO) {
                return null;
            }

            @Override
            public boolean isExist(String email) {
                return false;
            }

            @Override
            public AppUserDTO loadUserByJmbg(Long jmbg) {
                return null;
            }
        };
    }
}
