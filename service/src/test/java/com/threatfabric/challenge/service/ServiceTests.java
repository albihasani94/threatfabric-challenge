package com.threatfabric.challenge.service;

import com.threatfabric.challenge.repository.NewDetectionRepository;
import com.threatfabric.challenge.repository.model.detection.NewDetectionEntity;
import com.threatfabric.challenge.service.api.dto.Detection;
import com.threatfabric.challenge.service.api.dto.DetectionReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @Mock
    private NewDetectionRepository newDetectionRepository;

    @InjectMocks
    private DetectionServiceImpl detectionService;

    @Test
    void testRegisterNewDetection() {
        DetectionReport detectionReport = new DetectionReport();

        Detection detection = new Detection();
        UUID aUUID = UUID.randomUUID();
        detection.setDetectionId(aUUID);

        detectionReport.setDetection(detection);

        when(newDetectionRepository.save(any(NewDetectionEntity.class))).then(returnsFirstArg());
        NewDetectionEntity registeredDetection = detectionService.registerNewDetection(detectionReport);

        assertEquals(aUUID, registeredDetection.getDetectionUUID());
    }

}
