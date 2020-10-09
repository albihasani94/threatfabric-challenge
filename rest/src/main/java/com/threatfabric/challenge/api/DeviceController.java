package com.threatfabric.challenge.api;

import com.threatfabric.challenge.service.api.DeviceService;
import com.threatfabric.challenge.service.api.dto.Detection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/{deviceId}/detections/")
    public ResponseEntity<List<Detection>> getAllForDevice(@PathVariable UUID deviceId) {
        var detections = deviceService.retrieveAllDetectionsByDeviceId(deviceId);
        if (detections.isEmpty()) {
            return noContentResponse();
        }
        return ResponseEntity.ok(detections);
    }

    private ResponseEntity noContentResponse() {
        return ResponseEntity.noContent().build();
    }

}
