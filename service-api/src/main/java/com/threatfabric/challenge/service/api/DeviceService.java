package com.threatfabric.challenge.service.api;

import com.threatfabric.challenge.service.api.dto.Detection;

import java.util.List;
import java.util.UUID;

public interface DeviceService {
    List<Detection> retrieveAllDetectionsByDeviceId(UUID deviceId);
}
