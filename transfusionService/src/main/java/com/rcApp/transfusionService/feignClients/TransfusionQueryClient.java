package com.rcApp.transfusionService.feignClients;

import com.rcApp.transfusionService.entitety.TransfusionQuery;
import com.rcApp.transfusionService.models.AppUserDTO;
import com.rcApp.transfusionService.models.RequestByType;
import com.rcApp.transfusionService.models.TransfusionQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("transfusionQueryService")
public interface TransfusionQueryClient {
 

    @PostMapping(value = "/api/v1/queryById")
    public TransfusionQuery getQueryById(@RequestBody TransfusionQueryDTO transfusionQueryDTO);

    @PostMapping("/api/v1/updateQuery")
    public TransfusionQuery update(@RequestBody TransfusionQuery transfusionQuery);

    @PostMapping("/api/v1/deleteQuery")
    public void delete(@RequestBody TransfusionQuery transfusionQuery);


    @PostMapping("/api/v1/getListOfRecipientQuery")
    public List<TransfusionQuery> getListOfRecipientQuery(@RequestBody AppUserDTO appUserDTO);

    // all query by type transfusion
    // todo: only medic can access
    @PostMapping(value = "/api/v1/allQueryOfHuByType")
    public List<TransfusionQuery> allQuHuByT(@RequestBody RequestByType types); //proveriti dali radi bes httprequesta
}
