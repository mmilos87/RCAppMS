package com.rcApp.rcTransfusionQueryService.controlers;

import com.rcApp.rcTransfusionQueryService.entitety.TransfusionQuery;
import com.rcApp.rcTransfusionQueryService.models.AppUserDTO;
import com.rcApp.rcTransfusionQueryService.models.RequestByType;
import com.rcApp.rcTransfusionQueryService.models.TransfusionQueryDTO;
import com.rcApp.rcTransfusionQueryService.services.TransfusionQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class TransfusionQueryController extends MainController {

    private final TransfusionQueryService transfusionQueryService;

    // create new query
    @PostMapping(value = "/query")
    public TransfusionQuery createNewQuery(
            @RequestBody TransfusionQueryDTO transfusionQuery, HttpServletRequest httpServletRequest) {
        TransfusionQuery query =
                transfusionQueryService.createOrUpdateTransfusionQuery(
                        transfusionQuery.getRecipient(),
                        transfusionQuery.getTransfusionType(),
                        transfusionQuery.getRequiredUnits(),
                        httpServletRequest);
        return query;
    }
    @PostMapping("/updateQuery")
    public TransfusionQuery update(@RequestBody TransfusionQuery transfusionQuery){
        return transfusionQueryService.updateQuery(transfusionQuery);

    }
    @PostMapping("/deleteQuery")
    public void delete(@RequestBody TransfusionQuery transfusionQuery){
        transfusionQueryService.deleteQuery(transfusionQuery);

    }

    @PostMapping("/getListOfRecipientQuery")
    public List<TransfusionQuery> getListOfRecipientQuery(@RequestBody AppUserDTO appUserDTO){
        return transfusionQueryService.getRecipientQueries(appUserDTO);

    }
    // all query by type transfusion
    // todo: only medic can access
    @PostMapping(value = "/allQueryOfHuByType")
    public List<TransfusionQuery> allQuHuByT(
            @RequestBody RequestByType types, HttpServletRequest httpServletRequest) {
        List<TransfusionQuery> allQueryOfHospitalUnitByType =
                transfusionQueryService.getAllQueryOfHospitalUnitByType(httpServletRequest, types.getTransfusionType());
        return allQueryOfHospitalUnitByType;
    }



    @PostMapping(value = "/api/v1/queryById")
    public TransfusionQuery getQueryById(@RequestBody TransfusionQueryDTO transfusionQueryDTO){

        return transfusionQueryService.getQueryById(transfusionQueryDTO);
    };

}
