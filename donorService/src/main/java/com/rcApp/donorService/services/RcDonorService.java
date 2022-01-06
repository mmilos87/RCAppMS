package com.rcApp.donorService.services;


import com.rcApp.donorService.entitety.AppUser;
import com.rcApp.donorService.entitety.RcUserDonor;
import com.rcApp.donorService.models.AppUserDTO;
import com.rcApp.donorService.models.BloodRequestDTO;
import com.rcApp.donorService.models.RcDonorDto;

import java.util.List;

public interface RcDonorService {
 RcUserDonor updateDonor(RcUserDonor donor);
 RcUserDonor createDonor(RcDonorDto appUserDTO);
void deleteDonor(RcUserDonor rcUserDonor);
 RcUserDonor getDonorByAppUser(AppUser appUser);
 RcUserDonor getDonorByAppUserJmbg(AppUserDTO appUserDTO);
 List<RcUserDonor> findDonorListForSpecificBloodType(BloodRequestDTO bloodRequestDTO);

 Long deleteDonorById(Long id);

 RcUserDonor getDonorById(Long id);
}
