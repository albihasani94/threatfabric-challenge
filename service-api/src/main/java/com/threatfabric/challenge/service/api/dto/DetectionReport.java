package com.threatfabric.challenge.service.api.dto;

import java.util.Objects;

public class DetectionReport {

    private Device device;
    private Detection detection;

    public DetectionReport() {
    }

    public DetectionReport(Device device, Detection detection) {
        this.device = device;
        this.detection = detection;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetectionReport that = (DetectionReport) o;
        return Objects.equals(device, that.device) &&
                Objects.equals(detection, that.detection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, detection);
    }

    @Override
    public String toString() {
        return "DetectionReport{" +
                "device=" + device +
                ", detection=" + detection +
                '}';
    }
}
