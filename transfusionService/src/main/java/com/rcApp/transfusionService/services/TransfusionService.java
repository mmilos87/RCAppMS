package com.rcApp.transfusionService.services;


import com.rcApp.transfusionService.entitety.*;
import com.rcApp.transfusionService.helpers.enums.TransfusionTypes;

import javax.servlet.http.HttpServletRequest;

public interface TransfusionService {


    RcTransfusion nonDedicatedTransfusionCompleted(
            HttpServletRequest httpServletRequest, RcUserDonor donor, TransfusionTypes types);

    DedicatedTransfusions dedicatedTransfusionCompleted(
            Long transfusionQueryId, HttpServletRequest httpServletRequest, RcUserDonor donor);

    RejectedTransfusions rejectedTransfusionsSave(
            TransfusionTypes type, HttpServletRequest httpServletRequest, RcUserDonor donor, String note);

}
