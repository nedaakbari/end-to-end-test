package org.example.sample4.shahkar.mobile;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;

public class ReusableAuthTest {
    static String username = "Mci";
    static String password = "mci12345678";

    private static final String token = "djfjrfoijroijoreihgoirehgoirehg";
    //    static RequestSpecification spec = new RequestSpecBuilder().setAuth(basic(username, password)).build();
    static RequestSpecification spec = new RequestSpecBuilder().setAuth(basic(username, password)).build();

    @BeforeMethod
    void setup() {
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    @Test
    void authTest() {
        given()
                .spec(spec)
                .get("https://api.github.com/users/repos")
                .prettyPeek()
                .then()
                .statusCode(200)
                .log().all();
    }
}
