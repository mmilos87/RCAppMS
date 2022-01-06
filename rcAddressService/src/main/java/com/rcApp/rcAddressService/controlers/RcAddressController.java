package com.rcApp.rcAddressService.controlers;

import com.rcApp.rcAddressService.entitety.RcAddress;
import com.rcApp.rcAddressService.entitety.UserCity;
import com.rcApp.rcAddressService.models.RcAddressDTO;
import com.rcApp.rcAddressService.services.RcAddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class RcAddressController extends MainController{

  private final RcAddressService addressService;

  @PostMapping(value ="/saveOrUpdateAddress")
  public RcAddress saveOrUpdateAddress(@RequestBody RcAddressDTO request)  {
 return   addressService.saveOrUpdateAddress(request);
  }

    @GetMapping(value ="/getAllCities")
    public List<UserCity> getAllCities()  {
        return   addressService.getAllCities();
    }


}
