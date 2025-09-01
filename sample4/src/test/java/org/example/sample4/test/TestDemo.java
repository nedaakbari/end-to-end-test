package org.example.sample4.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestDemo {
//    static final String BASE_URL = "https://api.github.com";

    @BeforeTest
    void setup() {
        baseURI = "https://api.github.com";
    }

    @Test
    void peekSampleTestDemo() {
        System.out.println("peekSampleTestDemo => "+Thread.currentThread().getName());
        given()
                .when()
                .get(baseURI)
                .then()
                .statusCode(200);
    }
}
