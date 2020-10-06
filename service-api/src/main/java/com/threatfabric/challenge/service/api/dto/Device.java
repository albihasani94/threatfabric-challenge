package com.threatfabric.challenge.service.api.dto;

import java.util.Objects;

// TODO: Work on this dto fields
public class Device {

    private Long deviceId;
    private String deviceType;
    private String deviceModel;
    private String osVersion;

    public Device(Long deviceId, String deviceType, String deviceModel, String osVersion) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.deviceModel = deviceModel;
        this.osVersion = osVersion;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(deviceId, device.deviceId) &&
                Objects.equals(deviceType, device.deviceType) &&
                Objects.equals(deviceModel, device.deviceModel) &&
                Objects.equals(osVersion, device.osVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, deviceType, deviceModel, osVersion);
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId=" + deviceId +
                ", deviceType='" + deviceType + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }
}
