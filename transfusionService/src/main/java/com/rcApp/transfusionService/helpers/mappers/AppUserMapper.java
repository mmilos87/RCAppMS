package com.rcApp.transfusionService.helpers.mappers;

import com.rcApp.transfusionService.entitety.AppUser;
import com.rcApp.transfusionService.models.AppUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE= Mappers.getMapper(AppUserMapper.class);
    AppUser dtoToAppUser(AppUserDTO appUserDTO);
    AppUserDTO appUserToDto(AppUser appUser);
}
