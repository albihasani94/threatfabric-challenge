package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.repository.model.detection.DetectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectionRepository extends CrudRepository<DetectionEntity, Long> {

}