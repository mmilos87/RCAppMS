package com.rcAp.rcHospitalUnitService.mappers;

import com.rcAp.rcHospitalUnitService.entitety.HospitalUnit;
import com.rcAp.rcHospitalUnitService.models.HospitalUnitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HospitalUnitMapper {
    HospitalUnitMapper INSTANCE= Mappers.getMapper(HospitalUnitMapper.class);
    HospitalUnit getUnit(HospitalUnitDTO hospitalUnitDTO);
    HospitalUnitDTO getDTO(HospitalUnit hospitalUnit);
}
