package com.threatfabric.challenge.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private LocalDateTime timeOfIncident;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, LocalDateTime timeOfIncident) {
        this.message = message;
        this.timeOfIncident = timeOfIncident;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeOfIncident() {
        return timeOfIncident;
    }

    public void setTimeOfIncident(LocalDateTime timeOfIncident) {
        this.timeOfIncident = timeOfIncident;
    }
}
