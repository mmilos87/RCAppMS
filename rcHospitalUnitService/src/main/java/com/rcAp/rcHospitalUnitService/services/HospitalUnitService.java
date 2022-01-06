package com.rcAp.rcHospitalUnitService.services;

import com.rcAp.rcHospitalUnitService.entitety.HospitalUnit;
import com.rcAp.rcHospitalUnitService.models.HospitalUnitDTO;

import java.util.List;


public interface HospitalUnitService  {

  HospitalUnit  saveOrUpdateHospitalUnit(HospitalUnitDTO hospitalUnitDTO);
  HospitalUnit getHosUnitByName(String name);

  List<String> getAllHospitalUNitNames();

}

