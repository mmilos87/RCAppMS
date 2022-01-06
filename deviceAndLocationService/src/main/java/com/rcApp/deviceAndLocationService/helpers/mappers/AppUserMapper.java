package com.rcApp.deviceAndLocationService.helpers.mappers;

import com.rcApp.deviceAndLocationService.entitety.AppUser;
import com.rcApp.deviceAndLocationService.models.AppUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE= Mappers.getMapper(AppUserMapper.class);
    AppUser dtoToAppUser(AppUserDTO appUserDTO);
    AppUserDTO appUserToDto(AppUser appUser);
}
