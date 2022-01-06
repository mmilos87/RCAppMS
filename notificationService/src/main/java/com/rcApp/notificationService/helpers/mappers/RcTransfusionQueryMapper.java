package com.rcApp.notificationService.helpers.mappers;

import com.rcApp.notificationService.entitety.TransfusionQuery;
import com.rcApp.notificationService.models.TransfusionQueryDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RcTransfusionQueryMapper {
    RcTransfusionQueryMapper INSTANCE= Mappers.getMapper(RcTransfusionQueryMapper.class);
    TransfusionQuery getEntity(TransfusionQueryDTO transfusionQueryDTO);
    TransfusionQueryDTO getDTO(TransfusionQuery transfusionQuery);

}
