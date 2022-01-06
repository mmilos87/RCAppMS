package com.rcApp.authProviderService.helpers.enums;

public enum AppMessages {
    USER_WITH_THAT_EMAIL_NOT_FOUND("user with email %s not found"),

    JMBG_IS_TOO_LONG("jmbg is not valid, jmbg must have 13 characters"),

    JMBG_CHECKSUM_IS_NOT_CORRECT("jmbg is not valid, checksum is not correct"),

    JMBG_DATE_IS_NOT_VALID("jmbg is not valid, date is not valid"),

    USER_ALREADY_REGISTERED("User is already registered"),

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
