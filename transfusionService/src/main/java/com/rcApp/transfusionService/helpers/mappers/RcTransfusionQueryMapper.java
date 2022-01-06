package com.rcApp.transfusionService.helpers.mappers;

import com.rcApp.transfusionService.entitety.TransfusionQuery;
import com.rcApp.transfusionService.models.TransfusionQueryDTO;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

@MapperConfig
public interface RcTransfusionQueryMapper {
    RcTransfusionQueryMapper INSTANCE= Mappers.getMapper(RcTransfusionQueryMapper.class);
    TransfusionQuery getEntity(TransfusionQueryDTO transfusionQueryDTO);
    TransfusionQueryDTO getDTO(TransfusionQuery transfusionQuery);

}
