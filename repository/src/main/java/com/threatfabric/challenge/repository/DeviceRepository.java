package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.repository.model.device.DeviceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, UUID> {
}
