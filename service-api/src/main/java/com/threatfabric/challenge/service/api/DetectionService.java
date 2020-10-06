package com.threatfabric.challenge.service.api;

import com.threatfabric.challenge.repository.model.detection.NewDetectionEntity;
import com.threatfabric.challenge.service.api.dto.DetectionReport;

import java.util.UUID;

public interface DetectionService {

    NewDetectionEntity registerNewDetection(DetectionReport detectionReport);
    NewDetectionEntity retrieveDetectionByUUID(UUID detectionUUID);

}
