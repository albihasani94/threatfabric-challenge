package com.threatfabric.challenge;

import com.threatfabric.challenge.service.api.dto.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class RestIntegrationTests {

    private static RequestSpecification spec;

    @LocalServerPort
    private int port;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12-alpine");

    @BeforeAll
    static void createSpecification() {
        spec = new RequestSpecBuilder().setBaseUri("http://localhost/api").build();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    void actuatorTest() {
        given()
                .spec(spec)
                .when()
                .port(port)
                .get("/actuator/health/")
                .then()
                .body("status", equalTo("UP"));
    }

    @Test
    void registerDetectionTest() {
        var detection = new NoDetection();
        detection.setTime(Instant.EPOCH.getEpochSecond());
        var device = new Device();
        device.setDeviceId(UUID.randomUUID());
        device.setDeviceType("IOS");
        device.setOsVersion("14.0");
        device.setDeviceModel("iPhone 6s");
        var detectionReport = new DetectionReport();
        detectionReport.setDevice(device);
        detectionReport.setDetections(List.of(detection));

        given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(detectionReport)
                .when()
                .port(port)
                .post("/detections/")
                .then()
                .body("device.deviceType", equalTo(device.getDeviceType()));
    }

    @Test
    void registerDetectionWithUnsupportedDevice() {
        var detection = new NewDetection();
        detection.setDetectionUuid(UUID.randomUUID());
        detection.setNameOfApp("ING Bank");
        detection.setTypeOfApp("Banking");
        detection.setTime(Instant.EPOCH.getEpochSecond());
        var device = new Device();
        device.setDeviceId(UUID.randomUUID());
        device.setDeviceType("BREAK");
        device.setDeviceModel("P20 Pro");
        device.setOsVersion("10.0");
        var detectionReport = new DetectionReport();
        detectionReport.setDevice(device);
        detectionReport.setDetections(List.of(detection));

        given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(detectionReport)
                .when()
                .port(port)
                .post("/detections/")
                .then()
                .statusCode(400)
                .body("message", containsString("not supported"));
    }

    @Test
    void retrieveNoDetectionsForDeviceId() {
        var deviceId = UUID.randomUUID();

        given()
                .spec(spec)
                .pathParam("deviceId", deviceId)
                .when()
                .port(port)
                .get("/devices/{deviceId}/detections/")
                .then()
                .statusCode(204);
    }

}
