package com.threatfabric.challenge.service.api.dto;

public class DetectionReport {
    private Device device;
    // TODO: Turn to list
    private Detection detection;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Detection getDetection() {
        return detection;
    }

    public void setDetection(Detection detection) {
        this.detection = detection;
    }
}
