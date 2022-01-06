package com.rcApp.transfusionService.feignClients;

import com.rcApp.transfusionService.entitety.AppUser;
import com.rcApp.transfusionService.entitety.RcUserDonor;
import com.rcApp.transfusionService.models.AppUserDTO;
import com.rcApp.transfusionService.models.RcDonorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("donorService")
public interface RcDonorClient {


    @PostMapping(value = "/api/v1/createDonor")
    public RcUserDonor createDonor(@RequestBody RcDonorDto rcDonorDto);

    @PostMapping(value = "/api/v1/updateDonor")
    public RcUserDonor updateDonor(@RequestBody RcUserDonor rcUserDonor);

    @PostMapping(value = "/api/v1/deleteDonor")
    public void deleteDonor(@RequestBody RcUserDonor rcUserDonor);

    @PostMapping(value = "/api/v1/getDonorByAppUser")
    public RcUserDonor getDonor(@RequestBody AppUser appUser);

    @PostMapping(value = "/api/v1/getDonorByJmbg")
    public RcUserDonor getDonorByJmbg(@RequestBody AppUserDTO appUserDTO);
}
