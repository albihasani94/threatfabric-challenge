package com.threatfabric.challenge;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ChallengeApplicationTests {

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
    void contextLoads() {
    }

    @Test
    void helloTest() {
        given()
                .spec(spec)
                .when()
                .port(port)
                .get("/hello/")
                .then()
                .body(containsString("hello"));
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

}
