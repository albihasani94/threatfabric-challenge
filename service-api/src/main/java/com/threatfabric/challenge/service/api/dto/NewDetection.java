package com.threatfabric.challenge.service.api.dto;

import java.util.UUID;

public class NewDetection extends Detection {

    // These fields apply to new detection
    private UUID detectionUuid;
    private String nameOfApp;
    private String typeOfApp;

    public UUID getDetectionUuid() {
        return detectionUuid;
    }

    public void setDetectionUuid(UUID detectionUuid) {
        this.detectionUuid = detectionUuid;
    }

    public String getNameOfApp() {
        return nameOfApp;
    }

    public void setNameOfApp(String nameOfApp) {
        this.nameOfApp = nameOfApp;
    }

    public String getTypeOfApp() {
        return typeOfApp;
    }

    public void setTypeOfApp(String typeOfApp) {
        this.typeOfApp = typeOfApp;
    }
}
