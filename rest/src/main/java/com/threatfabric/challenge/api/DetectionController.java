package com.threatfabric.challenge.api;

import com.threatfabric.challenge.service.api.DetectionService;
import com.threatfabric.challenge.service.api.dto.Detection;
import com.threatfabric.challenge.service.api.dto.DetectionReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.threatfabric.challenge.util.Util.noContentResponse;

@RestController
@RequestMapping("/detections")
public class DetectionController {

    private final DetectionService detectionService;

    public DetectionController(DetectionService detectionService) {
        this.detectionService = detectionService;
    }

    @PostMapping("/")
    public ResponseEntity<DetectionReport> registerDetections(@Valid @RequestBody DetectionReport detectionReport) {
        var result = detectionService.registerDetectionReport(detectionReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<List<Detection>> retrieveAllDetections() {
        var detections = detectionService.retrieveDetections();
        if (detections.isEmpty()) {
            return noContentResponse();
        }
        return ResponseEntity.ok(detections);
    }

    @GetMapping
    public ResponseEntity<List<Detection>> retrieveFilteredDetections(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) UUID detectionUuid,
            @RequestParam(required = false) String nameOfApp,
            @RequestParam(required = false) String deviceType) {
        var detections = detectionService.retrieveFilteredDetections(id, detectionUuid, nameOfApp, deviceType);
        return detections.isEmpty() ? noContentResponse() : ResponseEntity.ok(detections);
    }
}
