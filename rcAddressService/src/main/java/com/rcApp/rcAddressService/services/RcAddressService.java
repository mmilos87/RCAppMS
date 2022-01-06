package com.rcApp.rcAddressService.services;

import com.rcApp.rcAddressService.entitety.RcAddress;
import com.rcApp.rcAddressService.entitety.UserCity;
import com.rcApp.rcAddressService.models.RcAddressDTO;

import java.util.List;

public interface RcAddressService {
    RcAddress saveOrUpdateAddress(RcAddressDTO rcAddressDTO);
    List<UserCity> getAllCities();

}
