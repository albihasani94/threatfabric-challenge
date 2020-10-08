package com.threatfabric.challenge.service.converters;

import com.threatfabric.challenge.repository.NewDetectionRepository;
import com.threatfabric.challenge.repository.model.detection.DetectionEntity;
import com.threatfabric.challenge.repository.model.detection.NewDetectionEntity;
import com.threatfabric.challenge.repository.model.detection.NoDetectionEntity;
import com.threatfabric.challenge.repository.model.detection.ResolvedDetectionEntity;
import com.threatfabric.challenge.repository.model.device.DeviceEntity;
import com.threatfabric.challenge.service.api.dto.Detection;
import com.threatfabric.challenge.service.api.dto.NewDetection;
import com.threatfabric.challenge.service.api.dto.NoDetection;
import com.threatfabric.challenge.service.api.dto.ResolvedDetection;

import java.util.function.Function;

public class DetectionConverter {

    private DetectionConverter() {
    }

    public static Function<Detection, DetectionEntity> fromNewDetectionDtoToEntity(DeviceEntity deviceEntity) {
        return detection -> {
            var newDetectionDto = (NewDetection) detection;
            var detectionEntity = new NewDetectionEntity();
            detectionEntity.setNameOfApp(newDetectionDto.getNameOfApp());
            detectionEntity.setTypeOfApp(newDetectionDto.getTypeOfApp());
            detectionEntity.setTime(newDetectionDto.getTime());
            detectionEntity.setDetectionUuid(newDetectionDto.getDetectionUuid());
            detectionEntity.setDevice(deviceEntity);
            return detectionEntity;
        };
    }

    public static Function<Detection, DetectionEntity> fromResolvedDetectionDtoToEntity(DeviceEntity deviceEntity, NewDetectionRepository newDetectionRepository) {
        return detection -> {
            var resolvedDetectionDto = (ResolvedDetection) detection;
            var entity = new ResolvedDetectionEntity();
            entity.setTime(resolvedDetectionDto.getTime());
            entity.setEarlierDetection(newDetectionRepository.findByDetectionUuid(resolvedDetectionDto.getDetectionUuid()).orElse(null));
            entity.setDevice(deviceEntity);
            return entity;
        };
    }

    public static Function<Detection, DetectionEntity> fromNoDetectionDtoToEntity(DeviceEntity deviceEntity) {
        return detection -> {
            var noDetectionDto = (NoDetection) detection;
            var entity = new NoDetectionEntity();
            entity.setTime(noDetectionDto.getTime());
            entity.setDevice(deviceEntity);
            return entity;
        };
    }

    public static Function<DetectionEntity, Detection> fromNewDetectionEntityToDto() {
        return entity -> {
            var newDetectionEntity = (NewDetectionEntity) entity;
            var detection = new NewDetection();
            detection.setId(newDetectionEntity.getId());
            detection.setDetectionUuid(newDetectionEntity.getDetectionUuid());
            detection.setTime(newDetectionEntity.getTime());
            detection.setNameOfApp(newDetectionEntity.getNameOfApp());
            detection.setTypeOfApp(newDetectionEntity.getTypeOfApp());
            return detection;
        };
    }

    public static Function<DetectionEntity, Detection> fromResolvedEntityToDto() {
        return entity -> {
            var resolvedDetectionEntity = (ResolvedDetectionEntity) entity;
            var detection = new ResolvedDetection();
            detection.setId(resolvedDetectionEntity.getId());
            detection.setDetectionUuid(resolvedDetectionEntity.getEarlierDetection().getDetectionUuid());
            detection.setTime(resolvedDetectionEntity.getTime());
            return detection;
        };
    }
}
