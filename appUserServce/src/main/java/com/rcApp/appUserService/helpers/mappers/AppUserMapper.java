package com.rcApp.appUserService.helpers.mappers;

import com.rcApp.appUserService.entitety.AppUser;
import com.rcApp.appUserService.models.AppUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE= Mappers.getMapper(AppUserMapper.class);
    AppUser dtoToAppUser(AppUserDTO appUserDTO);
    AppUserDTO appUserToDto(AppUser appUser);
}
