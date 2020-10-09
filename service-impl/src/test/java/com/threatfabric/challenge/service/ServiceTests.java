package com.threatfabric.challenge.service;

import com.threatfabric.challenge.repository.DetectionRepository;
import com.threatfabric.challenge.repository.DeviceRepository;
import com.threatfabric.challenge.repository.model.detection.NewDetectionEntity;
import com.threatfabric.challenge.repository.model.device.DeviceEntity;
import com.threatfabric.challenge.repository.model.device.DeviceType;
import com.threatfabric.challenge.service.api.dto.DetectionReport;
import com.threatfabric.challenge.service.api.dto.Device;
import com.threatfabric.challenge.service.api.dto.NewDetection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @Mock
    private DetectionRepository detectionRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DetectionServiceImpl detectionService;

    @Test
    void testRegisterNewDetection() {
        var detectionReport = new DetectionReport();

        var detection = new NewDetection();
        var detectionUuid = UUID.randomUUID();
        detection.setDetectionUuid(detectionUuid);
        detectionReport.setDetections(List.of(detection));

        var device = new Device();
        var deviceUuid = UUID.randomUUID();
        device.setDeviceId(deviceUuid);
        device.setDeviceType("IOS");
        device.setDeviceModel("iPhone 6S");
        detectionReport.setDevice(device);

        var deviceEntity = new DeviceEntity();
        deviceEntity.setType(DeviceType.IOS);
        deviceEntity.setModel(device.getDeviceModel());
        deviceEntity.setOsVersion(device.getOsVersion());

        when(deviceRepository.findById(deviceUuid)).thenReturn(Optional.of(deviceEntity));
        when(deviceRepository.save(any(DeviceEntity.class))).thenReturn(deviceEntity);
        when(detectionRepository.save(any(NewDetectionEntity.class))).then(returnsFirstArg());
        var report = detectionService.registerDetectionReport(detectionReport);

        assertEquals(deviceUuid, report.getDevice().getDeviceId());
    }

}
