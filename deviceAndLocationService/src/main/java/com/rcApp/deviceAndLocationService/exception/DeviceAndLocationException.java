package com.rcApp.deviceAndLocationService.exception;

import com.rcApp.deviceAndLocationService.helpers.enums.AppMessages;
import org.springframework.security.core.AuthenticationException;

public class DeviceAndLocationException  extends AuthenticationException {
    private final AppMessages appMessages;

    public DeviceAndLocationException(AppMessages appMessages) {
        super(appMessages.getMessage());
        this.appMessages = appMessages;
    }

    public AppMessages getAppMessages() {
        return appMessages;
    }
}
