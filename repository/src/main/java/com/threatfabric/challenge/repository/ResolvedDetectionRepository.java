package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.repository.model.detection.ResolvedDetectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResolvedDetectionRepository extends CrudRepository<ResolvedDetectionEntity, Long> {

    List<ResolvedDetectionEntity> findAll();
    Optional<ResolvedDetectionEntity> findByEarlierDetectionDetectionUuid(UUID earlierDetectionId);

}
