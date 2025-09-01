package org.example.sample4.test;

import org.example.sample4.AllureRestAssuredFilter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestApi {

    @BeforeTest
    void setup() {
        baseURI = "http://localhost:8080/test1";
    }

    @Test
    void apiTest() {
        System.out.println("apiTest => "+Thread.currentThread().getName());
        given()
                .log().all()   // همه جزئیات request (headers, body, params) رو لاگ کن
                .filter(new AllureRestAssuredFilter())
                .when()
                .get(baseURI)
                .then()
                .statusCode(200)
                .log().all();  // همه جزئیات response رو لاگ کن
    }

}
