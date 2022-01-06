package com.rcApp.deviceAndLocationService.helpers.enums;

public enum AppMessages {
    DEVICE_IS_ENABLED("device is enabled"),
    DEVICE_IS_NOT_ENABLED("device is not enabled"),
    DEVICE_IS_NOT_IN_LIST("device is not in list");

    private final String message;

    AppMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
