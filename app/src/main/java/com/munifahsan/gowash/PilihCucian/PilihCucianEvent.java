package com.munifahsan.gowash.PilihCucian;

public class PilihCucianEvent {
    public static final int onError = 0;
    public static final int onSuccess = 1;

    private int eventType;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
