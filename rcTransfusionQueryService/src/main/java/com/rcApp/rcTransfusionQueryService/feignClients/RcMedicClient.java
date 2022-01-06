package com.rcApp.rcTransfusionQueryService.feignClients;

import com.rcApp.rcTransfusionQueryService.entitety.AppUser;
import com.rcApp.rcTransfusionQueryService.entitety.RcUserMedic;
import com.rcApp.rcTransfusionQueryService.models.AppUserDTO;
import com.rcApp.rcTransfusionQueryService.models.RcMedicDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("medicService")
public interface RcMedicClient {

    @PostMapping("/api/v1/createMedic")
    public RcUserMedic createMedic(@RequestBody  RcMedicDTO rcMedicDTO);

    @PostMapping("/api/v1/updateMedic")
    public RcUserMedic updateMedic(@RequestBody  RcUserMedic rcUserMedic);

    @PostMapping("/api/v1/deleteMedic")
    public void deleteMedic(@RequestBody RcUserMedic rcUserMedic);

    @PostMapping("/api/v1/getMedicBYAppUser")
    public RcUserMedic getMedicBYAppUser(@RequestBody  AppUserDTO appUserDTOr);

    @PostMapping("/api/v1/getMedicBYJmbg")
    public RcUserMedic getMedicBYAppUserJMBG(@RequestBody  AppUserDTO appUserDTO);

}
