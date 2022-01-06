package com.rcApp.rcTransfusionQueryService.services;

import com.rcApp.rcTransfusionQueryService.entitety.AppUser;
import com.rcApp.rcTransfusionQueryService.entitety.HospitalUnit;
import com.rcApp.rcTransfusionQueryService.entitety.RcUserMedic;
import com.rcApp.rcTransfusionQueryService.entitety.TransfusionQuery;
import com.rcApp.rcTransfusionQueryService.feignClients.AppUserClient;
import com.rcApp.rcTransfusionQueryService.feignClients.NotificationClient;
import com.rcApp.rcTransfusionQueryService.feignClients.RcMedicClient;
import com.rcApp.rcTransfusionQueryService.helpers.enums.TransfusionTypes;
import com.rcApp.rcTransfusionQueryService.helpers.mappers.AppUserMapper;
import com.rcApp.rcTransfusionQueryService.helpers.mappers.RcTransfusionQueryMapper;
import com.rcApp.rcTransfusionQueryService.jwt.JwtTokenHelper;
import com.rcApp.rcTransfusionQueryService.models.AppUserDTO;
import com.rcApp.rcTransfusionQueryService.models.TransfusionQueryDTO;
import com.rcApp.rcTransfusionQueryService.repos.TransfusionQueryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class TransfusionQueryServiceImpl implements TransfusionQueryService {

    private final TransfusionQueryRepository transfusionQueryRepository;
    private final RcMedicClient rcMedicClient;
    private final NotificationClient notificationClient;
    private final JwtTokenHelper jwtTokenHelper;
    private final AppUserClient appUserClient;

    @Override
    public List<TransfusionQuery> getRecipientQueries(AppUserDTO appUserDTO) {
        appUserDTO=appUserClient.loadUserByJmbg(appUserDTO.getJmbg());

        return transfusionQueryRepository.findByRecipient(AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO));
    }

    @Override
    public TransfusionQuery createOrUpdateTransfusionQuery(
            AppUser recipient, TransfusionTypes type, Long units, HttpServletRequest httpServletRequest) {
        RcUserMedic medic = getMedicFromJWT(httpServletRequest);
        TransfusionQuery transfusionQuery =
                TransfusionQuery.builder()
                        .transfusionType(type)
                        .requiredUnits(units)
                        .hospitalUnit(medic.getHospitalUnit())
                        .createdAt(LocalDateTime.now())
                        .recipient(recipient)
                        .rcUserMedic(medic)
                        .build();
        // todo popraviti da kod bude malo citljivijij
        transfusionQueryRepository
                .findByRecipientBYType(recipient, type)
                .ifPresentOrElse(
                        query -> {
                            query.setRequiredUnits(units);
                            TransfusionQuery save = transfusionQueryRepository.save(query);
                            transfusionQuery.setId(save.getId());
                            notificationClient.initialNotifications(RcTransfusionQueryMapper.INSTANCE.getDTO(save));
                        },
                        () -> {
                            TransfusionQuery save = transfusionQueryRepository.save(transfusionQuery);
                            notificationClient.initialNotifications(RcTransfusionQueryMapper.INSTANCE.getDTO(save));
                            transfusionQuery.setId(save.getId());
                        });
        return transfusionQuery;
    }

    @Override
    public TransfusionQuery getQueryById(TransfusionQueryDTO transfusionQueryDTO) {
        return transfusionQueryRepository.getById(transfusionQueryDTO.getId());
    }

    @Override
    public List<TransfusionQuery> getAllQueryOfHospitalUnitByType(HttpServletRequest httpServletRequest, TransfusionTypes types) {

        HospitalUnit hospitalUnit = getMedicFromJWT(httpServletRequest).getHospitalUnit();
        List<TransfusionQuery> aQuery =
                transfusionQueryRepository
                        .findAllQueryOfHUnitByType(hospitalUnit, types)
                        //todo izuzetak...
                        .orElseThrow(() -> new IllegalArgumentException("No such a query"));
        return aQuery;
    }

    @Override
    public TransfusionQuery updateQuery(TransfusionQuery transfusionQuery) {
        return transfusionQueryRepository.save(transfusionQuery);
    }

    @Override
    public void deleteQuery(TransfusionQuery transfusionQuery) {
        transfusionQueryRepository.delete(transfusionQuery);
    }

    private RcUserMedic getMedicFromJWT(HttpServletRequest request) {
        AppUser appUser = jwtTokenHelper.extractAppUserFromJwtClaims(request);
        return rcMedicClient.getMedicBYAppUser(AppUserMapper.INSTANCE.appUserToDto(appUser));
    }

}
