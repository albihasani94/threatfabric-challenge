package com.threatfabric.challenge.service.converters;

import com.threatfabric.challenge.repository.model.device.DeviceEntity;
import com.threatfabric.challenge.service.api.dto.Device;

import java.util.function.Function;

public class DeviceConverter {

    private DeviceConverter() {
    }

    public static Function<DeviceEntity, Device> fromDeviceEntityToDto() {
        return entity -> {
            var device = new Device();
            device.setDeviceId(entity.getId());
            device.setDeviceModel(entity.getModel());
            device.setOsVersion(entity.getOsVersion());
            device.setDeviceType(entity.getType().name());
            return device;
        };
    }
}
