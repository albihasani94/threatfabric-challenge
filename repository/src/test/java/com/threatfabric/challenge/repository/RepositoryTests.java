package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.repository.model.detection.NewDetectionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests {

    @Container
    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12-alpine");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Autowired
    private NewDetectionRepository newDetectionRepository;

    @Test
    void repositoryIsLoaded() {
        assertThat(newDetectionRepository).isNotNull();
    }

    @Test
    void persistNewDetectionAndFindByUUID() {
        NewDetectionEntity entity = new NewDetectionEntity();

        entity.setTime(Instant.EPOCH.getEpochSecond());
        entity.setTypeOfApp("Banking");
        UUID detectionUUID = UUID.randomUUID();

        entity.setDetectionUUID(detectionUUID);
        newDetectionRepository.save(entity);

        NewDetectionEntity entityFromDb = newDetectionRepository.findByDetectionUUID(detectionUUID);
        assertEquals(entity, entityFromDb);
    }

    @SpringBootApplication
    static class TestConfiguration {
    }

}
