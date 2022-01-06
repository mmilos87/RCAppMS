package com.rcApp.authProviderService.exception;

import com.rcApp.authProviderService.helpers.enums.AppMessages;

import javax.naming.AuthenticationException;

public class DeviceAndLocationException extends AuthenticationException {
    private final AppMessages appMessages;

    public DeviceAndLocationException(AppMessages appMessages) {
        super(appMessages.getMessage());
        this.appMessages = appMessages;
    }

    public AppMessages getAppMessages() {
        return appMessages;
    }
}
