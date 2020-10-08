package com.threatfabric.challenge.service.api;

import com.threatfabric.challenge.service.api.dto.Detection;
import com.threatfabric.challenge.service.api.dto.DetectionReport;
import com.threatfabric.challenge.service.api.dto.Device;

import java.util.List;

public interface DetectionService {

    Device registerDetectionReport(DetectionReport detectionReport);
    List<Detection> retrieveDetections();

}
