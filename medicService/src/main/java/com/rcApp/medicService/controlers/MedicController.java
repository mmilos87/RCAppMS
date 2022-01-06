package com.rcApp.medicService.controlers;

import com.rcApp.medicService.entitety.AppUser;
import com.rcApp.medicService.entitety.RcUserMedic;
import com.rcApp.medicService.models.AppUserDTO;
import com.rcApp.medicService.models.RcMedicDTO;
import com.rcApp.medicService.services.RcUserMedicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class MedicController extends MainController{
    private final RcUserMedicService medicService;

    @PostMapping("createMedic")
    public RcUserMedic createMedic(RcMedicDTO rcMedicDTO){

        return medicService.createMedic(rcMedicDTO);
    }


    @PostMapping("updateMedic")
    public RcUserMedic updateMedic(@RequestBody  RcUserMedic rcUserMedic){

        return medicService.updateMedic(rcUserMedic);
    }

    @PostMapping("deleteMedic")
    public void deleteMedic(@RequestBody  RcUserMedic rcUserMedic){

         medicService.deleteMedic(rcUserMedic);
    }

    @PostMapping("getMedicBYAppUser")
    public RcUserMedic getMedicBYAppUser(@RequestBody  AppUser appUser){

        return medicService.getMedicByAppUser(appUser);
    }
    @PostMapping("getMedicBYJmbg")
    public RcUserMedic getMedicBYAppUserJMBG(@RequestBody AppUserDTO appUserDTO){

        return medicService.getMedicByAppUserJmbg(appUserDTO);
    }






}
