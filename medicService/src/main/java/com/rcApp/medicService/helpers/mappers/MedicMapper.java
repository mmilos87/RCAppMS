package com.rcApp.medicService.helpers.mappers;

import com.rcApp.medicService.entitety.RcUserMedic;
import com.rcApp.medicService.models.RcMedicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicMapper {

     MedicMapper INSTANCE= Mappers.getMapper( MedicMapper.class);
    RcUserMedic getMedic(RcMedicDTO rcMedicDTO);
    RcMedicDTO  getDTO(RcUserMedic rcUserMedic);
}
