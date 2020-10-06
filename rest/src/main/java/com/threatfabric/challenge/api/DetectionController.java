package com.threatfabric.challenge.api;

import com.threatfabric.challenge.service.api.DetectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detections")
public class DetectionController {

    private static final Logger LOGGER = LogManager.getLogger(DetectionController.class.getName());

    @Autowired
    DetectionService detectionService;
    
}
