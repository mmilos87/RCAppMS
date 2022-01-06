package com.rcApp.notificationService.feignClients;

import com.rcApp.notificationService.entitety.AppUser;
import com.rcApp.notificationService.entitety.RcUserDonor;
import com.rcApp.notificationService.models.AppUserDTO;
import com.rcApp.notificationService.models.BloodRequestDTO;
import com.rcApp.notificationService.models.RcDonorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    @PostMapping(value = "/api/v1/getDonorList")
    public List<RcUserDonor> getListDonors(BloodRequestDTO bloodRequestDTO);
}
