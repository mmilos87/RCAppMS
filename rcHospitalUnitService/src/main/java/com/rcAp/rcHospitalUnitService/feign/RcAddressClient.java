package com.rcAp.rcHospitalUnitService.feign;

import com.rcAp.rcHospitalUnitService.entitety.RcAddress;
import com.rcAp.rcHospitalUnitService.entitety.UserCity;
import com.rcAp.rcHospitalUnitService.models.RcAddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("addressService")
public interface RcAddressClient {
    @PostMapping(value ="/api/v1/saveOrUpdateAddress")
    public RcAddress saveOrUpdateAddress(@RequestBody RcAddressDTO request);
    @GetMapping(value ="/api/v1/getAllCities")
    public List<UserCity> getAllCities();


}
