package com.rcApp.transfusionService.helpers.mappers;


import com.rcApp.transfusionService.entitety.RcAddress;
import com.rcApp.transfusionService.models.RcAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RcAddressMapper {
    RcAddressMapper INSTANCE= Mappers.getMapper(RcAddressMapper.class);
    RcAddress getAddress(RcAddressDTO rcAddressDTO);
    RcAddressDTO getDTO(RcAddress rcAddress);
}
