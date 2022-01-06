package com.rcApp.donorService.helpers.mappers;

import com.rcApp.donorService.entitety.RcUserDonor;
import com.rcApp.donorService.models.RcDonorDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DonorMapper {
    DonorMapper INSTANCE= Mappers.getMapper(DonorMapper.class);
    RcUserDonor getDonor(RcDonorDto rcDonorDto);
    RcDonorDto getDTO(RcUserDonor rcUserDonor);
}
