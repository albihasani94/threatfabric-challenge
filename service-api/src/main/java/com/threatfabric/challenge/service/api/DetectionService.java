package com.threatfabric.challenge.service.api;

import com.threatfabric.challenge.service.api.dto.Detection;
import com.threatfabric.challenge.service.api.dto.DetectionReport;

import java.util.List;

public interface DetectionService {

    DetectionReport registerDetectionReport(DetectionReport detectionReport);
    List<Detection> retrieveDetections();

}
