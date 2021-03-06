package com.threatfabric.challenge.service.api.dto;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Device {
    private UUID deviceId;
    @NotBlank(message = "Device type is mandatory")
    private String deviceType;
    @NotBlank(message = "Device model is mandatory")
    private String deviceModel;
    @NotBlank(message = "Os version is mandatory")
    private String osVersion;

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
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
}
