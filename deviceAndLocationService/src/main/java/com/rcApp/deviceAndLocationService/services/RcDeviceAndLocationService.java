package com.rcApp.deviceAndLocationService.services;


import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.rcApp.deviceAndLocationService.exception.DeviceAndLocationException;
import com.rcApp.deviceAndLocationService.helpers.enums.AppMessages;
import com.rcApp.deviceAndLocationService.models.DeviceAndLocationDTO;
import com.rcApp.deviceAndLocationService.models.RcConfirmDto;

import java.io.IOException;

public interface RcDeviceAndLocationService {
    AppMessages verifyDevice(DeviceAndLocationDTO deviceAndLocationDTO) throws IOException, GeoIp2Exception, DeviceAndLocationException;

    String enableDevice(RcConfirmDto rcConfirmDto);
}
