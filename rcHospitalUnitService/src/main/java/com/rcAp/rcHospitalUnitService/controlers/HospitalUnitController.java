package com.rcAp.rcHospitalUnitService.controlers;

import com.rcAp.rcHospitalUnitService.entitety.HospitalUnit;
import com.rcAp.rcHospitalUnitService.models.HospitalUnitDTO;
import com.rcAp.rcHospitalUnitService.services.HospitalUnitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class HospitalUnitController extends MainController {

    private final HospitalUnitService hospitalUnitService;


    @PostMapping(value = "/saveHospitalUnit")
    public HospitalUnit saveOrUpdateHU(@RequestBody HospitalUnitDTO hospitalUnitdto) {
        return hospitalUnitService.saveOrUpdateHospitalUnit(hospitalUnitdto);
    }

    @GetMapping(value = "/getAllHospitalUnit")
    public List<String> getAllHUNames() {
        return hospitalUnitService.getAllHospitalUNitNames();
    }


    @PostMapping(value = "/getHosUnitByName")
    public HospitalUnit getHUByName(@RequestBody HospitalUnitDTO hospitalUnitDTO) {
        return hospitalUnitService.getHosUnitByName(hospitalUnitDTO.getHospitalUnitName());
    }

}
