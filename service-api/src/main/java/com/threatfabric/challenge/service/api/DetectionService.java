package com.threatfabric.challenge.service.api;

import com.threatfabric.challenge.service.api.dto.Detection;
import com.threatfabric.challenge.service.api.dto.DetectionReport;

import java.util.List;
import java.util.UUID;

public interface DetectionService {

    DetectionReport registerDetectionReport(DetectionReport detectionReport);
    List<Detection> retrieveDetections();
    List<Detection> retrieveFilteredDetections(Long id, UUID detectionUuid, String nameOfApp, String deviceType);

}
