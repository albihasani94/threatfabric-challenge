package com.threatfabric.challenge.service;

import com.threatfabric.challenge.repository.DetectionRepository;
import com.threatfabric.challenge.repository.DeviceRepository;
import com.threatfabric.challenge.repository.NewDetectionRepository;
import com.threatfabric.challenge.repository.model.detection.DetectionEntity;
import com.threatfabric.challenge.repository.model.device.DeviceEntity;
import com.threatfabric.challenge.repository.model.device.DeviceType;
import com.threatfabric.challenge.service.api.DetectionService;
import com.threatfabric.challenge.service.api.dto.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static com.threatfabric.challenge.service.converters.DetectionConverter.*;
import static com.threatfabric.challenge.service.converters.DeviceConverter.fromDeviceEntityToDto;

@Service
public class DetectionServiceImpl implements DetectionService {

    private final DeviceRepository deviceRepository;

    private final DetectionRepository detectionRepository;

    private final NewDetectionRepository newDetectionRepository;

    public DetectionServiceImpl(DeviceRepository deviceRepository, DetectionRepository detectionRepository, NewDetectionRepository newDetectionRepository) {
        this.deviceRepository = deviceRepository;
        this.detectionRepository = detectionRepository;
        this.newDetectionRepository = newDetectionRepository;
    }

    @Override
    public Device registerDetectionReport(DetectionReport detectionReport) {
        var device = detectionReport.getDevice();
        var detection = detectionReport.getDetection();

        // TODO: perform validation
        var deviceEntity = createOrUpdateDeviceEntity(device);

        functionFromDetectionType(deviceEntity)
                .get(detection.getClass())
                .andThen(detectionRepository::save)
                .apply(detection);

        // TODO: Include some detection data on the returned response
        return fromDeviceEntityToDto().apply(deviceEntity);
    }

    private DeviceEntity createOrUpdateDeviceEntity(Device device) {
        return deviceRepository.findById(device.getDeviceId())
                .or(createDeviceEntity())
                .map(withCurrentDeviceInfo(device))
                .map(deviceRepository::save)
                .orElseThrow(couldNotProcessDeviceData());
    }

    private Supplier<Optional<DeviceEntity>> createDeviceEntity() {
        return () -> Optional.of(new DeviceEntity());
    }

    private UnaryOperator<DeviceEntity> withCurrentDeviceInfo(Device device) {
        return deviceEntity -> {
            deviceEntity.setId(device.getDeviceId());
            deviceEntity.setOsVersion(device.getOsVersion());
            deviceEntity.setModel(device.getDeviceModel());
            deviceEntity.setType(DeviceType.valueOf(device.getDeviceType()));
            return deviceEntity;
        };
    }

    private Supplier<RuntimeException> couldNotProcessDeviceData() {
        return () -> new RuntimeException("Could not process device data");
    }

    @Override
    public List<Detection> retrieveDetections() {
        var result = detectionRepository.findAll();
        // debug result
        // process result
        return Collections.emptyList();
    }

    private Map<Class<? extends Detection>, Function<Detection, DetectionEntity>> functionFromDetectionType(DeviceEntity deviceEntity) {
        return Map.of(
                NewDetection.class, fromNewDetectionDtoToEntity(deviceEntity),
                ResolvedDetection.class, fromResolvedDetectionDtoToEntity(deviceEntity, newDetectionRepository),
                NoDetection.class, fromNoDetectionDtoToEntity(deviceEntity)
        );
    }
}
