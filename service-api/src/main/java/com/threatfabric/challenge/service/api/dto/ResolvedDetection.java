package com.threatfabric.challenge.service.api.dto;

import java.util.UUID;

public class ResolvedDetection extends Detection {

    private UUID detectionUuid;

    public UUID getDetectionUuid() {
        return detectionUuid;
    }

    public void setDetectionUuid(UUID detectionUuid) {
        this.detectionUuid = detectionUuid;
    }
}
