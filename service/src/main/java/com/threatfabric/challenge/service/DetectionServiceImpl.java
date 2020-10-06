package com.threatfabric.challenge.service;

import com.threatfabric.challenge.repository.NewDetectionRepository;
import com.threatfabric.challenge.repository.model.detection.NewDetectionEntity;
import com.threatfabric.challenge.service.api.DetectionService;
import com.threatfabric.challenge.service.api.dto.DetectionReport;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DetectionServiceImpl implements DetectionService {

    private final NewDetectionRepository newDetectionRepository;

    public DetectionServiceImpl(NewDetectionRepository newDetectionRepository) {
        this.newDetectionRepository = newDetectionRepository;
    }

    // TODO: Convert to dto before returning to the client
    @Override
    public NewDetectionEntity registerNewDetection(DetectionReport detectionReport) {
        return newDetectionRepository.save(fromDetectionReport(detectionReport));
    }

    @Override
    public NewDetectionEntity retrieveDetectionByUUID(UUID detectionUUID) {
        return newDetectionRepository.findByDetectionUUID(detectionUUID);
    }

    private NewDetectionEntity fromDetectionReport(DetectionReport detectionReport) {
        NewDetectionEntity entity = new NewDetectionEntity();
        // TODO: Implement this
        entity.setDetectionUUID(detectionReport.getDetection().getDetectionId());
        return entity;
    }
}
