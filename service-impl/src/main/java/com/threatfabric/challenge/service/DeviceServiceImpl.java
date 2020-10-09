package com.threatfabric.challenge.service;

import com.threatfabric.challenge.repository.DetectionRepository;
import com.threatfabric.challenge.service.api.DeviceService;
import com.threatfabric.challenge.service.api.dto.Detection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.threatfabric.challenge.service.util.Mapper.fromEntityToDto;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DetectionRepository detectionRepository;

    @Override
    public List<Detection> retrieveAllDetectionsByDeviceId(UUID deviceId) {
        return detectionRepository.findAllByDeviceId(deviceId)
                .stream()
                .map(fromEntityToDto())
                .collect(Collectors.toList());
    }
}
