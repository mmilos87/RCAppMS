package com.rcApp.loginAttemptService.helpers.mappers;

import com.rcApp.loginAttemptService.entitety.AppUser;
import com.rcApp.loginAttemptService.models.AppUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE= Mappers.getMapper(AppUserMapper.class);
    AppUser dtoToAppUser(AppUserDTO appUserDTO);
    AppUserDTO appUserToDto(AppUser appUser);
}
