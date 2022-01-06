package com.rcApp.rcTransfusionQueryService.helpers.mappers;

import com.rcApp.rcTransfusionQueryService.entitety.TransfusionQuery;
import com.rcApp.rcTransfusionQueryService.models.TransfusionQueryDTO;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

@MapperConfig
public interface RcTransfusionQueryMapper {
    RcTransfusionQueryMapper INSTANCE= Mappers.getMapper(RcTransfusionQueryMapper.class);
    TransfusionQuery getEntity(TransfusionQueryDTO transfusionQueryDTO);
    TransfusionQueryDTO getDTO(TransfusionQuery transfusionQuery);

}
