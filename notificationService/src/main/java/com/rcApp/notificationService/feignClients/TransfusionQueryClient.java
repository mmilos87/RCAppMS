package com.rcApp.notificationService.feignClients;

import com.rcApp.notificationService.entitety.TransfusionQuery;
import com.rcApp.notificationService.models.AppUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("transfusionQueryService")
public interface TransfusionQueryClient {

    @PostMapping("/api/v1/updateQuery")
    public TransfusionQuery update( @RequestBody TransfusionQuery transfusionQuery);

    @PostMapping("/api/v1/deleteQuery")
    public void delete( @RequestBody TransfusionQuery transfusionQuery);


    @PostMapping("/api/v1/getListOfRecipientQuery")
    public List<TransfusionQuery> getListOfRecipientQuery( @RequestBody AppUserDTO appUserDTO);

}
