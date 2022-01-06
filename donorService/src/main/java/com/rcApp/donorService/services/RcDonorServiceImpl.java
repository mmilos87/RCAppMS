package com.rcApp.donorService.services;

import com.rcApp.donorService.entitety.AppUser;
import com.rcApp.donorService.entitety.RcUserDonor;
import com.rcApp.donorService.feignClients.AppUserClient;
import com.rcApp.donorService.helpers.mappers.DonorMapper;
import com.rcApp.donorService.helpers.mappers.AppUserMapper;
import com.rcApp.donorService.models.AppUserDTO;
import com.rcApp.donorService.models.BloodRequestDTO;
import com.rcApp.donorService.models.RcDonorDto;
import com.rcApp.donorService.repos.RcUserDonorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class RcDonorServiceImpl implements  RcDonorService {

  private RcUserDonorsRepository donorsRepository;
  private AppUserClient appUserClient;
  // todo crud metode
  // da se prvo proveri dal postoji user u db ako ne postoji provo da se kreira user u pa se potom gadja ovaj kontroler
  // ovde stize u donor dto user entitet


  @Override
  public RcUserDonor getDonorByAppUser(AppUser appUser){
    return donorsRepository.findByAppUser(appUser).orElseThrow(
            () -> new IllegalStateException("Donor no exist"));
  }

  @Override
  public void deleteDonor(RcUserDonor rcUserDonor){

    donorsRepository.delete(rcUserDonor);
  }

  @Override
  public RcUserDonor updateDonor(RcUserDonor donor)   {

    return donorsRepository.saveAndFlush(donor);
  }

  @Override
  public RcUserDonor getDonorByAppUserJmbg(AppUserDTO appUserDTO) {
    appUserDTO=appUserClient.loadUserByJmbg(appUserDTO.getJmbg());
    return getDonorByAppUser(AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO));
  }

  @Override
  public List<RcUserDonor> findDonorListForSpecificBloodType(BloodRequestDTO bloodRequestDTO) {
    Pageable page = PageRequest.of(0, bloodRequestDTO.getDonorToNotify());
    return donorsRepository.findByBloodType(bloodRequestDTO.getCompatibleBloodTypes(),
                              bloodRequestDTO.getUserCity(),
                              bloodRequestDTO.getBloodCondition(),
                              bloodRequestDTO.getPlateletsCondition(),
                              bloodRequestDTO.getBloodPlasmaCondition(),
                              bloodRequestDTO.getRecipient(), page)
                             .get();
            }

  @Override
  public Long deleteDonorById(Long id) {
    donorsRepository.findById(id).ifPresentOrElse(this::deleteDonor,
    ()-> new IllegalStateException(" Donor ne postoji"));
    return id;
  }

  @Override
  public RcUserDonor getDonorById(Long id) {
    return donorsRepository.findById(id).orElseThrow(()-> new IllegalStateException("donor ne postoji"));
  }

  @Override
  public RcUserDonor createDonor(RcDonorDto rcDonorDto) {
    RcUserDonor donor =
         donorsRepository.save(DonorMapper.INSTANCE.getDonor(rcDonorDto));
    return donor;
  }

}
