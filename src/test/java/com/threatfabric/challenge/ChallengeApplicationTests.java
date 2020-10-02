package com.threatfabric.challenge;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ChallengeApplicationTests {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12-alpine");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
    }

    @Test
    void helloTest() {
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri("http://localhost/api").setPort(port).build();

        given()
                .spec(spec)
                .when()
                .get("/hello/")
                .then()
                .body(containsString("hello"));
    }

}
