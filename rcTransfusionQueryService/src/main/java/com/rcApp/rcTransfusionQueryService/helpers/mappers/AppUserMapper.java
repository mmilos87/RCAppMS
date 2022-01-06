package com.rcApp.rcTransfusionQueryService.helpers.mappers;

import com.rcApp.rcTransfusionQueryService.entitety.AppUser;
import com.rcApp.rcTransfusionQueryService.models.AppUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE= Mappers.getMapper(AppUserMapper.class);
    AppUser dtoToAppUser(AppUserDTO appUserDTO);
    AppUserDTO appUserToDto(AppUser appUser);
}
