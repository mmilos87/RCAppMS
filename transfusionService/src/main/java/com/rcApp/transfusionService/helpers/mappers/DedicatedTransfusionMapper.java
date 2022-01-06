package com.rcApp.transfusionService.helpers.mappers;

import com.rcApp.transfusionService.entitety.DedicatedTransfusions;
import com.rcApp.transfusionService.models.DedicatedTransfusionsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DedicatedTransfusionMapper {
DedicatedTransfusionMapper INSTANCE=Mappers.getMapper(DedicatedTransfusionMapper.class);
DedicatedTransfusions getEntity(DedicatedTransfusionsDTO dedicatedTransfusionsDTO);
DedicatedTransfusionsDTO getDTO(DedicatedTransfusions dedicatedTransfusions);
}
