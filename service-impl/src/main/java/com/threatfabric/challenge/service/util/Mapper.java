package com.threatfabric.challenge.service.util;

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

import java.util.Map;
import java.util.function.Function;

import static com.threatfabric.challenge.service.converters.DetectionConverter.*;
import static com.threatfabric.challenge.service.converters.DetectionConverter.fromNoDetectionEntityToDto;

public class Mapper {

    private Mapper() {
    }

    public static Function<Detection, DetectionEntity> fromDtoToEntity(DeviceEntity deviceEntity, NewDetectionRepository newDetectionRepository) {
        return detection -> {
            var map = Map.of(
                    NewDetection.class, fromNewDetectionDtoToEntity(deviceEntity),
                    ResolvedDetection.class, fromResolvedDetectionDtoToEntity(deviceEntity, newDetectionRepository),
                    NoDetection.class, fromNoDetectionDtoToEntity(deviceEntity)
            );

            return map.get(detection.getClass()).apply(detection);
        };
    }

    public static Function<DetectionEntity, Detection> fromEntityToDto() {
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
