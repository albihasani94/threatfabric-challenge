package com.threatfabric.challenge.service;

import com.threatfabric.challenge.repository.DetectionRepository;
import com.threatfabric.challenge.repository.DeviceRepository;
import com.threatfabric.challenge.repository.NewDetectionRepository;
import com.threatfabric.challenge.repository.model.device.DeviceEntity;
import com.threatfabric.challenge.repository.model.device.DeviceType;
import com.threatfabric.challenge.service.api.DetectionService;
import com.threatfabric.challenge.service.api.dto.Detection;
import com.threatfabric.challenge.service.api.dto.DetectionReport;
import com.threatfabric.challenge.service.api.dto.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static com.threatfabric.challenge.service.converters.DeviceConverter.fromDeviceEntityToDto;
import static com.threatfabric.challenge.service.util.Mapper.fromDtoToEntity;
import static com.threatfabric.challenge.service.util.Mapper.fromEntityToDto;

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
                .map(fromDtoToEntity(deviceEntity, newDetectionRepository))
                .map(detectionRepository::save)
                .map(fromEntityToDto())
                .collect(Collectors.toList());

        device = fromDeviceEntityToDto().apply(deviceEntity);

        return new DetectionReport(device, detections);
    }

    @Override
    public List<Detection> retrieveDetections() {
        return detectionRepository.findAll()
                .stream()
                .map(fromEntityToDto())
                .collect(Collectors.toList());
    }

    @Override
    public List<Detection> retrieveFilteredDetections(Long id, UUID detectionUuid, String nameOfApp, String deviceType) {
        var deviceTypeEnum = DeviceType.valueFromString(deviceType);
        return detectionRepository.findAllByParams(id, detectionUuid, nameOfApp, deviceTypeEnum)
                .stream()
                .map(fromEntityToDto())
                .collect(Collectors.toList());
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
            deviceEntity.setType(DeviceType.valueFromString(device.getDeviceType()));
            return deviceEntity;
        };
    }

    private Supplier<RuntimeException> couldNotProcessDeviceData() {
        return () -> new RuntimeException("Could not process device data");
    }
}