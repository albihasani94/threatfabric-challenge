package com.threatfabric.challenge.repository.model.device;

import com.threatfabric.challenge.repository.exception.DeviceNotSupportedException;

public enum DeviceType {
    WEB, ANDROID, IOS;

    public static DeviceType valueFromString(String value) {
        try {
            return DeviceType.valueOf(value);
        } catch (Exception e) {
            throw new DeviceNotSupportedException(value);
        }
    }
}
