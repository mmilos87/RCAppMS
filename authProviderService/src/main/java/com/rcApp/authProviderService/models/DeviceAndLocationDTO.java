package com.rcApp.authProviderService.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceAndLocationDTO {
    private AppUserDTO appUserDTO;
    private String deviceDetailsHeader;
    private String xForwardedForIpHeader;
    private String remoteAddress;

    public DeviceAndLocationDTO(HttpServletRequest request, AppUserDTO appUserDTO) {
        this.appUserDTO = appUserDTO;
        this.deviceDetailsHeader=request.getHeader("user-agent");
        this.xForwardedForIpHeader =request.getHeader("x-forwarded-for");
        this.remoteAddress= request.getRemoteAddr();
    }
}
