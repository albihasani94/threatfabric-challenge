package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.repository.model.detection.DetectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetectionRepository extends CrudRepository<DetectionEntity, Long> {
    List<DetectionEntity> findAll();
}