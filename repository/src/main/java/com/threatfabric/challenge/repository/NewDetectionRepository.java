package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.repository.model.detection.NewDetectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NewDetectionRepository extends CrudRepository<NewDetectionEntity, Long> {

    List<NewDetectionEntity> findAll();
    NewDetectionEntity findByDetectionUUID(UUID detectionUUID);

}
