package com.rcApp.transfusionService.helpers.mappers;

import com.rcApp.transfusionService.entitety.RcTransfusion;
import com.rcApp.transfusionService.models.RcTransfusionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RcTransfusionMapper {
RcTransfusionMapper INSTANCE= Mappers.getMapper(RcTransfusionMapper.class);
RcTransfusion getEntity(RcTransfusionDTO rcTransfusionDTO);
RcTransfusionDTO getDTO(RcTransfusion rcTransfusion);

}
