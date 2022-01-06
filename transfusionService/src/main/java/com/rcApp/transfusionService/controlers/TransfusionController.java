package com.rcApp.transfusionService.controlers;

import com.rcApp.transfusionService.entitety.*;
import com.rcApp.transfusionService.models.*;
import com.rcApp.transfusionService.services.TransfusionService;
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
public class TransfusionController extends MainController {

    private final TransfusionService transfusionService;



    // non dedicated transfusion
    @PostMapping(value = "/nondedicated")
    public RcTransfusion nonDedicatedTransfusion(
            @RequestBody RcTransfusionDTO requestTransfusion, HttpServletRequest httpServletRequest) {

        RcTransfusion rcTransfusion =
                transfusionService.nonDedicatedTransfusionCompleted(
                        httpServletRequest, requestTransfusion.getDonor(), requestTransfusion.getType());
        return rcTransfusion;
    }

    // non dedicated transfusion
    @PostMapping(value = "/dedicated")
    public DedicatedTransfusions dedicatedTransfusion(
            @RequestBody DedicatedTransfusionsDTO dedicatedTransfusionsDTO,
            HttpServletRequest httpServletRequest) {
        DedicatedTransfusions dedicatedTransfusions =
                transfusionService.dedicatedTransfusionCompleted(
                        dedicatedTransfusionsDTO.getId(), httpServletRequest, dedicatedTransfusionsDTO.getTransfusion().getDonor());
        return dedicatedTransfusions;
    }

    //rejected transfusion
    @PostMapping(value = "/rejected")
    public RejectedTransfusions rejectedTransfusion(
            @RequestBody RejectedTransfusionsDTO rejectedTransfusionsDTO,
            HttpServletRequest httpServletRequest) {
        RejectedTransfusions rejectedTransfusions =
                transfusionService.rejectedTransfusionsSave(
                        rejectedTransfusionsDTO.getTransfusion().getType(),
                        httpServletRequest,
                        rejectedTransfusionsDTO.getTransfusion().getDonor(),
                        rejectedTransfusionsDTO.getNote());
        return rejectedTransfusions;
    }

}
