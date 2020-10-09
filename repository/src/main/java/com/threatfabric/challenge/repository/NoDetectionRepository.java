package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.repository.model.detection.NoDetectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoDetectionRepository extends CrudRepository<NoDetectionEntity, Long> {

    List<NoDetectionEntity> findAll();

}
