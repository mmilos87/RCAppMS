package com.rcApp.medicService.services;


import com.rcApp.medicService.entitety.AppUser;
import com.rcApp.medicService.entitety.RcUserMedic;
import com.rcApp.medicService.models.AppUserDTO;
import com.rcApp.medicService.models.RcMedicDTO;


public interface RcUserMedicService {
  RcUserMedic createMedic(RcMedicDTO rcMedicDTO);
RcUserMedic updateMedic(RcUserMedic rcUserMedic);
 void deleteMedic(RcUserMedic rcUserMedic);
 RcUserMedic getMedicByAppUser(AppUser appUser);
 RcUserMedic getMedicByAppUserJmbg(AppUserDTO appUserDTO);
}
