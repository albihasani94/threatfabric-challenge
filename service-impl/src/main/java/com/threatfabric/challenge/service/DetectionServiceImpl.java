package com.threatfabric.challenge.service;

import com.threatfabric.challenge.repository.DetectionRepository;
import com.threatfabric.challenge.repository.DeviceRepository;
import com.threatfabric.challenge.repository.NewDetectionRepository;
import com.threatfabric.challenge.repository.model.detection.DetectionEntity;
import com.threatfabric.challenge.repository.model.detection.NewDetectionEntity;
import com.threatfabric.challenge.repository.model.detection.NoDetectionEntity;
import com.threatfabric.challenge.repository.model.detection.ResolvedDetectionEntity;
import com.threatfabric.challenge.repository.model.device.DeviceEntity;
import com.threatfabric.challenge.repository.model.device.DeviceType;
import com.threatfabric.challenge.service.api.DetectionService;
import com.threatfabric.challenge.service.api.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

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
    public DetectionReport registerDetectionReport(DetectionReport detectionReport) {
        var device = detectionReport.getDevice();
        var detections = detectionReport.getDetections();

        // TODO: perform validation
        var deviceEntity = createOrUpdateDeviceEntity(device);

        detections = detections.stream()
                .map(fromDtoToEntity(deviceEntity))
                .map(detectionRepository::save)
                .map(fromEntityToDto())
                .collect(Collectors.toList());

        device = fromDeviceEntityToDto().apply(deviceEntity);

        return new DetectionReport(device, detections);
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
        // TODO: Add filters
        return detectionRepository.findAll()
                .stream()
                .map(fromEntityToDto())
                .collect(Collectors.toList());
    }

    private Function<Detection, DetectionEntity> fromDtoToEntity(DeviceEntity deviceEntity) {
        return detection -> {
            var map = Map.of(
                    NewDetection.class, fromNewDetectionDtoToEntity(deviceEntity),
                    ResolvedDetection.class, fromResolvedDetectionDtoToEntity(deviceEntity, newDetectionRepository),
                    NoDetection.class, fromNoDetectionDtoToEntity(deviceEntity)
            );

            return map.get(detection.getClass()).apply(detection);
        };
    }

    private Function<DetectionEntity, Detection> fromEntityToDto() {
        return detectionEntity -> {
            var map = Map.of(
                    NewDetectionEntity.class, fromNewDetectionEntityToDto(),
                    ResolvedDetectionEntity.class, fromResolvedEntityToDto(),
                    NoDetectionEntity.class, fromNoDetectionEntityToDto()
            );

            return map.get(detectionEntity.getClass()).apply(detectionEntity);
        };
    }

}
