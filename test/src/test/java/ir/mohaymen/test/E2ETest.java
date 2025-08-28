//package ir.mohaymen.test;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.*;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class E2ETest {
//
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
//            .withDatabaseName("testdb")
//            .withUsername("user")
//            .withPassword("pass");
//
//    @BeforeAll
//    void setup() {
//        postgres.start();
//
//        // تنظیمات دیتابیس Spring Boot
//        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
//        System.setProperty("spring.datasource.username", postgres.getUsername());
//        System.setProperty("spring.datasource.password", postgres.getPassword());
//
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 8080;
//    }
//
//    @AfterAll
//    void cleanup() {
//        postgres.stop();
//    }
//
//    @Test
//    void shouldRegisterUserAndFetchSubscription() {
//        // 1️⃣ Gateway Auth + Rate Limit شبیه‌سازی
//        String token = "dummy-jwt-token";
//
//        // 2️⃣ Insert subscription via Orchestration
//        given()
//            .contentType(ContentType.JSON)
//            .header("Authorization", "Bearer " + token)
//            .body("{\"nationalId\":\"1234567890\",\"birthDate\":\"1990-01-01\",\"name\":\"Alice\",\"serviceId\":1}")
//        .when()
//            .post("/orchestration/subscribe")
//        .then()
//            .statusCode(201)
//            .body("message", equalTo("Subscription created"));
//
//        // 3️⃣ Fetch subscription
//        given()
//            .header("Authorization", "Bearer " + token)
//        .when()
//            .get("/subscription/1234567890")
//        .then()
//            .statusCode(200)
//            .body("name", equalTo("Alice"))
//            .body("serviceId", equalTo(1));
//    }
//}
