package com.rcApp.transfusionService.helpers.mappers;

import com.rcApp.transfusionService.entitety.RcUserMedic;
import com.rcApp.transfusionService.models.RcMedicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicMapper {

     MedicMapper INSTANCE= Mappers.getMapper( MedicMapper.class);
    RcUserMedic getMedic(RcMedicDTO rcMedicDTO);
    RcMedicDTO getDTO(RcUserMedic rcUserMedic);
}
