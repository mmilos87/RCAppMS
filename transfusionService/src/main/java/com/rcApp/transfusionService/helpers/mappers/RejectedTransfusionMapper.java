package com.rcApp.transfusionService.helpers.mappers;

import com.rcApp.transfusionService.entitety.RejectedTransfusions;
import com.rcApp.transfusionService.models.RejectedTransfusionsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RejectedTransfusionMapper {
    RejectedTransfusionMapper INSTANCE= Mappers.getMapper(RejectedTransfusionMapper.class);
    RejectedTransfusions getEntity(RejectedTransfusionsDTO rejectedTransfusionsDTO);
    RejectedTransfusionsDTO getDTO(RejectedTransfusions rejectedTransfusions);

}
