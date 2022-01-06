package com.rcApp.transfusionService.services;

import com.rcApp.transfusionService.entitety.*;
import com.rcApp.transfusionService.feignClients.RcDonorClient;
import com.rcApp.transfusionService.feignClients.RcMedicClient;
import com.rcApp.transfusionService.feignClients.TransfusionQueryClient;
import com.rcApp.transfusionService.helpers.enums.TransfusionTypes;
import com.rcApp.transfusionService.helpers.mappers.AppUserMapper;
import com.rcApp.transfusionService.jwt.JwtTokenHelper;
import com.rcApp.transfusionService.models.TransfusionQueryDTO;
import com.rcApp.transfusionService.repos.DedicatedTransfusionRepository;
import com.rcApp.transfusionService.repos.RcTransfusionRepository;
import com.rcApp.transfusionService.repos.RejectedTransfusionsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Log4j2
public class TransfusionServiceImpl implements TransfusionService {

    private final TransfusionQueryClient transfusionQueryClient;
    private final RcTransfusionRepository rcTransfusionRepository;
    private final RcDonorClient rcDonorClient;
    private final RcMedicClient rcMedicClient;
    private final DedicatedTransfusionRepository dedicatedTransfusionRepository;
    private final RejectedTransfusionsRepository rejectedTransfusionsRepository;
    private final JwtTokenHelper jwtTokenHelper;


    @Override
    public RcTransfusion nonDedicatedTransfusionCompleted(
            HttpServletRequest httpServletRequest, RcUserDonor donor, TransfusionTypes types) {
        RcUserMedic medic = getMedicFromJWT(httpServletRequest);
        RcTransfusion rcTransfusion =
                transfusionSave(medic.getHospitalUnit(), types, medic, donor, false);
        return rcTransfusion;
    }

    @Override
    public DedicatedTransfusions dedicatedTransfusionCompleted(
            Long transfusionQueryId, HttpServletRequest httpServletRequest, RcUserDonor donor) {
        TransfusionQuery query = transfusionQueryClient.getQueryById(
                TransfusionQueryDTO.builder().id(transfusionQueryId).build());
        // todo izuzetak...
        RcUserMedic medic = getMedicFromJWT(httpServletRequest);
        RcTransfusion transfusion =
                transfusionSave(query.getHospitalUnit(), query.getTransfusionType(), medic, donor, true);
        query.setRequiredUnits(query.getRequiredUnits() - 1);
        if (query.getRequiredUnits() > 0) {
            transfusionQueryClient.update(query);
        } else {
            transfusionQueryClient.delete(query);
        }
        DedicatedTransfusions dedicatedTransfusions =
                dedicatedTransfusionRepository.save(
                        DedicatedTransfusions.builder()
                                .transfusion(transfusion)
                                .recipient(query.getRecipient())
                                .build());
        return dedicatedTransfusions;
    }



    @Override
    public RejectedTransfusions rejectedTransfusionsSave(
            TransfusionTypes type, HttpServletRequest httpServletRequest, RcUserDonor donor, String note) {
        RcUserMedic medic = getMedicFromJWT(httpServletRequest);
        donor.setHasBeenRejected(Boolean.TRUE);
        RcUserDonor userDonor = rcDonorClient.updateDonor(donor);
        switch (type) {
            case BLOOD:
                type = TransfusionTypes.BLOOD_REJECTED;
                break;
            case PLATELETS:
                type = TransfusionTypes.PLATELETS_REJECTED;
                break;
            case BLOOD_PLASMA:
                type = TransfusionTypes.BLOOD_PLASMA_REJECTED;
                break;
            default:
                break;
        }
        RcTransfusion transfusion =
                rcTransfusionRepository.save(
                        RcTransfusion.builder()
                                .hospitalUnit(medic.getHospitalUnit())
                                .type(type)
                                .date(LocalDateTime.now())
                                .rcUserMedic(medic)
                                .donor(userDonor)
                                .build());
        RejectedTransfusions rejectedTransfusions =
                rejectedTransfusionsRepository.save(
                        RejectedTransfusions.builder().transfusion(transfusion).note(note).build());
        return rejectedTransfusions;
    }


    private RcUserMedic getMedicFromJWT(HttpServletRequest request) {
        AppUser appUser = jwtTokenHelper.extractAppUserFromJwtClaims(request);
        return rcMedicClient.getMedicBYAppUser(AppUserMapper.INSTANCE.appUserToDto(appUser));
    }

    private RcTransfusion transfusionSave(
            HospitalUnit hospitalUnit,
            TransfusionTypes types,
            RcUserMedic medic,
            RcUserDonor donor,
            Boolean isDedicated) {
        RcTransfusion transfusion =
                rcTransfusionRepository.save(
                        RcTransfusion.builder()
                                .hospitalUnit(hospitalUnit)
                                .type(types)
                                .isDedicated(isDedicated)
                                .date(LocalDateTime.now())
                                .rcUserMedic(medic)
                                .donor(donor)
                                .build());

        switch (transfusion.getType()) {
            case BLOOD:
                donor.setDateLastBloodGiving(LocalDateTime.now());
                donor.setNumberOfBloodGiving(donor.getNumberOfBloodGiving() + 1);
                break;
            case PLATELETS:
                donor.setDateLastPlateletsGiving(LocalDateTime.now());
                donor.setNumberOfPlateletsGiving(donor.getNumberOfPlateletsGiving() + 1);
                break;
            case BLOOD_PLASMA:
                donor.setDateLastBloodPlasmaGiving(LocalDateTime.now());
                donor.setNumberOfBloodPlasmaGiving(donor.getNumberOfBloodPlasmaGiving() + 1);
        }
        rcDonorClient.updateDonor(donor);

        return transfusion;
    }

}
