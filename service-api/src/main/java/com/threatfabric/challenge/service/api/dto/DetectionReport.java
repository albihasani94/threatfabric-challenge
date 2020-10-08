package com.threatfabric.challenge.service.api.dto;

import java.util.List;

public class DetectionReport {
    private Device device;
    private List<Detection> detections;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public List<Detection> getDetections() {
        return detections;
    }

    public void setDetections(List<Detection> detections) {
        this.detections = detections;
    }
}
