package com.rcApp.rcTransfusionQueryService.services;


import com.rcApp.rcTransfusionQueryService.entitety.AppUser;
import com.rcApp.rcTransfusionQueryService.entitety.TransfusionQuery;
import com.rcApp.rcTransfusionQueryService.helpers.enums.TransfusionTypes;
import com.rcApp.rcTransfusionQueryService.models.AppUserDTO;
import com.rcApp.rcTransfusionQueryService.models.TransfusionQueryDTO;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TransfusionQueryService {

    TransfusionQuery createOrUpdateTransfusionQuery(
            AppUser recipient, TransfusionTypes type, Long units, HttpServletRequest httpServletRequest);
 
    List<TransfusionQuery> getAllQueryOfHospitalUnitByType(
            HttpServletRequest httpServletRequest, TransfusionTypes types);

    TransfusionQuery updateQuery(TransfusionQuery transfusionQuery);

    void deleteQuery(TransfusionQuery transfusionQuery);

    List<TransfusionQuery> getRecipientQueries(AppUserDTO appUserDTO);

    TransfusionQuery getQueryById(TransfusionQueryDTO transfusionQueryDTO);
}
