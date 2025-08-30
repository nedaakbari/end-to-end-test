package test;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class TestMyWebsite1 {

    @Test
    void testSomeRequest() {
        given()
                .filter(new AllureRestAssured())
                .get("https://jsonplaceholder.typicode.com/todos/1")
                .then()
                .body("userId", equalTo(1));
    }

    @Test
    void testSomeRequest2() {
        given()
                .filter(new AllureRestAssured())
                .get("https://jsonplaceholder.typicode.com/todos/1")
                .then()
                .body("userId", equalTo(1));
    }
}