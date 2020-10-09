package com.threatfabric.challenge.service.api.dto;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class NewDetection extends Detection {
    private UUID detectionUuid;
    @NotBlank(message = "Name of application is mandatory")
    private String nameOfApp;
    @NotBlank(message = "Type of application is mandatory")
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
