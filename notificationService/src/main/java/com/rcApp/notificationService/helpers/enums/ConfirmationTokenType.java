package com.rcApp.notificationService.helpers.enums;

public enum ConfirmationTokenType {
    USER_CONFIRMATION("Confirm your email"),
    DONOR_CONFIRM_TRANSFUSION("Confirm transfusion"),
    DEVICE_CONFIRMATION("Confirm your new device");
    private final String subject;

    ConfirmationTokenType(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
