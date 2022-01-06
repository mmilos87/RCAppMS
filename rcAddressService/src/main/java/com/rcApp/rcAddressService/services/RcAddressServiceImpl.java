package com.rcApp.rcAddressService.services;

import com.rcApp.rcAddressService.entitety.RcAddress;
import com.rcApp.rcAddressService.entitety.UserCity;
import com.rcApp.rcAddressService.helpers.mappers.RcAddressMapper;
import com.rcApp.rcAddressService.models.RcAddressDTO;
import com.rcApp.rcAddressService.repos.RCAddressRepository;
import com.rcApp.rcAddressService.repos.UserCItyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class RcAddressServiceImpl implements RcAddressService {
    private final UserCItyRepository userCItyRepository;
    private final RCAddressRepository addressRepository;

    @Override
    public RcAddress saveOrUpdateAddress(RcAddressDTO rcAddressDTO) {
        RcAddress rcAddress= RcAddressMapper.INSTANCE.getAddress(rcAddressDTO);
       return  addressRepository.save(rcAddress);
    }


    @Override
    public List<UserCity> getAllCities() {
      return userCItyRepository.findAll();
    }
}
