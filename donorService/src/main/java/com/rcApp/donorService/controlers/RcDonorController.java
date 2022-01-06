package com.rcApp.donorService.controlers;

import com.rcApp.donorService.entitety.AppUser;
import com.rcApp.donorService.entitety.RcUserDonor;
import com.rcApp.donorService.models.AppUserDTO;
import com.rcApp.donorService.models.BloodRequestDTO;
import com.rcApp.donorService.models.RcDonorDto;
import com.rcApp.donorService.services.RcDonorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class RcDonorController extends MainController {

    private final RcDonorService donorService;


    @PostMapping(value = "donor")
    public RcUserDonor create(@RequestBody RcDonorDto rcDonorDto,
                              HttpServletRequest httpServletRequest) {

        return donorService.createDonor(rcDonorDto);
    }

    @PutMapping(value = "donor/{id}")
    public RcUserDonor update(@PathVariable("id")Long id, @RequestBody RcUserDonor rcUserDonor) {

        return donorService.updateDonor(rcUserDonor);
    }

    @DeleteMapping(value = "donor/{id}")
    public Long delete(@PathVariable("id")Long id) {
        return donorService.deleteDonorById(id);
    }

    @GetMapping(value = "donor/{id}")
    public RcUserDonor get(@PathVariable("id")Long id) {
        return donorService.getDonorById(id);
    }

    @PostMapping(value = "updateDonor")
    public RcUserDonor updateDonor(@RequestBody RcUserDonor rcUserDonor) {
        return donorService.updateDonor(rcUserDonor);
    }

    @PostMapping(value = "createDonor")
    public RcUserDonor createDonor(@RequestBody RcDonorDto rcDonorDto, HttpServletRequest httpServletRequest) {

        return donorService.createDonor(rcDonorDto);
    }

    /**/
    @PostMapping(value = "deleteDonor")
    public void deleteDonor(@RequestBody RcUserDonor rcUserDonor) {
        donorService.deleteDonor(rcUserDonor);
    }

    /**/
    @PostMapping(value = "getDonorByAppUser")
    public RcUserDonor getDonor(@RequestBody AppUser appUser) {
        return donorService.getDonorByAppUser(appUser);
    }

    /**/
    @PostMapping(value = "getDonorByJmbg")
    public RcUserDonor getDonorByJmbg(@RequestBody AppUserDTO appUserDTO) {
        return donorService.getDonorByAppUserJmbg(appUserDTO);
    }

    /**/
    @PostMapping("getDonorList")
    public List<RcUserDonor> getListDonors(BloodRequestDTO bloodRequestDTO) {
        return donorService.findDonorListForSpecificBloodType(bloodRequestDTO);
    }

}
