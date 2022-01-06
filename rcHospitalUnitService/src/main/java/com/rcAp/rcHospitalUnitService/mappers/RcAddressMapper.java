package com.rcAp.rcHospitalUnitService.mappers;

import com.rcAp.rcHospitalUnitService.entitety.RcAddress;
import com.rcAp.rcHospitalUnitService.models.RcAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RcAddressMapper {
    RcAddressMapper INSTANCE= Mappers.getMapper(RcAddressMapper.class);
    RcAddress getAddress(RcAddressDTO rcAddressDTO);
    RcAddressDTO getDTO(RcAddress rcAddress);
}
