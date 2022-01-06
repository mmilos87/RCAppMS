package com.rcApp.donorService.helpers.mappers;

import com.rcApp.donorService.entitety.RcAddress;
import com.rcApp.donorService.models.RcAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RcAddressMapper {
    RcAddressMapper INSTANCE= Mappers.getMapper(RcAddressMapper.class);
    RcAddress getAddress(RcAddressDTO rcAddressDTO);
    RcAddressDTO getDTO(RcAddress rcAddress);
}
