package com.threatfabric.challenge.repository.exception;

import com.threatfabric.challenge.repository.model.device.DeviceType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DeviceNotSupportedException extends RuntimeException {
    public DeviceNotSupportedException(String deviceType) {
        super(String.format("Device type %s not supported. Must be one of %s", deviceType,
                Arrays.stream(DeviceType.values())
                        .map(Enum::toString)
                        .collect(Collectors.toList())
        ));
    }
}
