package com.rcApp.deviceAndLocationService.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceAndLocationDTO {
    private AppUserDTO appUserDTO;
    private String deviceDetailsHeader;
    private String xForwardedForIpHeader;
    private String remoteAddress;
}
