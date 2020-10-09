package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.repository.model.detection.DetectionEntity;
import com.threatfabric.challenge.repository.model.device.DeviceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DetectionRepository extends CrudRepository<DetectionEntity, Long> {
    List<DetectionEntity> findAll();

    @Query("Select distinct detection From DetectionEntity detection " +
            "LEFT JOIN NewDetectionEntity newDetection " +
            "ON detection.id = newDetection.id " +
            "LEFT JOIN ResolvedDetectionEntity resolvedDetection " +
            "ON detection.id = resolvedDetection.id " +
            "LEFT JOIN NoDetectionEntity noDetection " +
            "ON detection.id = newDetection.id " +
            "JOIN detection.device device " +
            "WHERE (detection.id = :id or :id is null) " +
            "AND (newDetection.nameOfApp = :nameOfApp or :nameOfApp is null) " +
            "AND (device.type = :deviceType or :deviceType is null) " +
            "AND (newDetection.detectionUuid = :detectionUuid " +
            "OR resolvedDetection.earlierDetection.detectionUuid = :detectionUuid " +
            "OR cast(:detectionUuid as org.hibernate.type.UUIDCharType) is null)"
    )
    List<DetectionEntity> findAllByParams(Long id, UUID detectionUuid, String nameOfApp, DeviceType deviceType);

    List<DetectionEntity> findAllByDeviceId(UUID deviceId);
}