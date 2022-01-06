package com.rcApp.medicService.services;

import com.rcApp.medicService.entitety.AppUser;
import com.rcApp.medicService.entitety.RcUserMedic;
import com.rcApp.medicService.feignClients.AppUserClient;
import com.rcApp.medicService.helpers.enums.AppMessages;
import com.rcApp.medicService.helpers.mappers.AppUserMapper;
import com.rcApp.medicService.helpers.mappers.MedicMapper;
import com.rcApp.medicService.models.AppUserDTO;
import com.rcApp.medicService.models.RcMedicDTO;
import com.rcApp.medicService.repos.RcUserMedicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RcUserMedicServiceImpl implements RcUserMedicService{

  private final RcUserMedicRepository medicRepository;
private final AppUserClient appUserClient;

  @Override
  public RcUserMedic createMedic(RcMedicDTO rcMedicDTO)  {

    boolean isMedicExist = medicRepository.findByAppUser(rcMedicDTO.getAppUser()).isPresent();
    if (!isMedicExist) {

      return medicRepository.save( MedicMapper.INSTANCE.getMedic(rcMedicDTO));
    } else {
      throw new IllegalStateException(AppMessages.USER_ALREADY_REGISTERED.getMessage());
    }
  }

  @Override
  public RcUserMedic updateMedic(RcUserMedic rcUserMedic) {
    return medicRepository.save(rcUserMedic);
  }

  @Override
  public void deleteMedic(RcUserMedic rcUserMedic) {
     medicRepository.delete(rcUserMedic);
  }

  @Override
  public RcUserMedic getMedicByAppUser(AppUser appUser) {
    return medicRepository.findByAppUser(appUser).get();
    //todo napraviti izuzetak ako appp user nije registirovan koa medic

  }

  @Override
  public RcUserMedic getMedicByAppUserJmbg(AppUserDTO appUserDTO) {
  appUserDTO =appUserClient.loadUserByJmbg(appUserDTO.getJmbg());
    return getMedicByAppUser(AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO));
  }


}
