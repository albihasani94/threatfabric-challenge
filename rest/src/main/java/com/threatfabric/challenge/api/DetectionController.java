package com.threatfabric.challenge.api;

import com.threatfabric.challenge.service.api.DetectionService;
import com.threatfabric.challenge.service.api.dto.Detection;
import com.threatfabric.challenge.service.api.dto.DetectionReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/detections")
public class DetectionController {

    private final DetectionService detectionService;

    public DetectionController(DetectionService detectionService) {
        this.detectionService = detectionService;
    }

    @PostMapping("/")
    public ResponseEntity<DetectionReport> registerDetections(@RequestBody DetectionReport detectionReport) {
        var result = detectionService.registerDetectionReport(detectionReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<List<Detection>> retrieveDetections(@RequestParam(required = false) UUID detectionId) {
        var detections = detectionService.retrieveDetections();
        return ResponseEntity.ok(detections);
    }

}
