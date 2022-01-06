package com.rcApp.deviceAndLocationService.controler;


import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.rcApp.deviceAndLocationService.helpers.enums.AppMessages;
import com.rcApp.deviceAndLocationService.models.DeviceAndLocationDTO;
import com.rcApp.deviceAndLocationService.models.RcConfirmDto;
import com.rcApp.deviceAndLocationService.services.RcDeviceAndLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "${application.controller.base}")
@RequiredArgsConstructor
public class DeviceAndLocationController extends MainController{

  private final RcDeviceAndLocationService rcDeviceAndLocationService;

  @PostMapping("${application.controller.endpoints.verify}")
  public AppMessages verifyDevice(@RequestBody DeviceAndLocationDTO deviceAndLocationDTO) throws IOException, GeoIp2Exception {
  return rcDeviceAndLocationService.verifyDevice(deviceAndLocationDTO);
  }

  @PostMapping("${application.controller.endpoints.enable}")
  String   enableDevice(@RequestBody RcConfirmDto rcConfirmDto){
    return rcDeviceAndLocationService.enableDevice(rcConfirmDto);
  }


}
