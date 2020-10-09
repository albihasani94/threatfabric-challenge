package com.threatfabric.challenge.service.api.dto;

import javax.validation.Valid;
import java.util.List;

public class DetectionReport {
    @Valid
    private Device device;
    private List<@Valid Detection> detections;

    public DetectionReport() {
    }

    public DetectionReport(Device device, List<Detection> detections) {
        this.device = device;
        this.detections = detections;
    }

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
