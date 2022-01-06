package com.rcApp.notificationService.helpers.mappers;


import com.rcApp.notificationService.entitety.RcAddress;
import com.rcApp.notificationService.models.RcAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RcAddressMapper {
    RcAddressMapper INSTANCE= Mappers.getMapper(RcAddressMapper.class);
    RcAddress getAddress(RcAddressDTO rcAddressDTO);
    RcAddressDTO getDTO(RcAddress rcAddress);
}
