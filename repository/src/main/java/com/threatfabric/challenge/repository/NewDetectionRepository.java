package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.repository.model.detection.NewDetectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NewDetectionRepository extends CrudRepository<NewDetectionEntity, Long> {

    List<NewDetectionEntity> findAll();
    Optional<NewDetectionEntity> findByDetectionUuid(UUID detectionUuid);

}
