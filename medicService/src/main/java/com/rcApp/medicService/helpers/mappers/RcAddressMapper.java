package com.rcApp.medicService.helpers.mappers;


import com.rcApp.medicService.entitety.RcAddress;
import com.rcApp.medicService.models.RcAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RcAddressMapper {
    RcAddressMapper INSTANCE= Mappers.getMapper(RcAddressMapper.class);
    RcAddress getAddress(RcAddressDTO rcAddressDTO);
    RcAddressDTO getDTO(RcAddress rcAddress);
}
