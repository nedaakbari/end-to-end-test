package org.example.sample4.headers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.sample4.Setup;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

public class ValidatableResponseTest {
    @Test
    void testUsingLocalRequestSpec() {
        RestAssured
                .given()
                .spec(Setup.requestSpecification)
                .when()
                .get()
                .then()
                .spec(Setup.responseSpecification);
    }


    @Test
    void basicValidatableExample() {
        RestAssured
                .given()
                .spec(Setup.requestSpecification)
                .when()
                .get()
                .then()
                .spec(Setup.responseSpecification)
                .header("x-ratelimit-limit", "60");

    }

    @Test
    void simpleHamcrestMatchers() {
        RestAssured.get(RestAssured.baseURI)
                .then()
                .statusCode(200)
                .statusCode(lessThan(300))
                .header("cache-control", containsStringIgnoringCase("max-age=60"))
                .time(lessThan(2L), TimeUnit.SECONDS)
                .header("etag", notNullValue())
                .header("etag", is(emptyString()));
    }

    Map<String, String> expectedHeaders = Map.of("content-encoding", "gzip",
            "access-control-allow-origin", "*");

    @Test
    void usingMapsToTestHeaders() {
        RestAssured.get(RestAssured.baseURI)
                .then()
                .headers(
                        "content-encoding", "gzip",
                        "access-control-allow-origin", "*",
                        "cache-control", containsString("public"))
                .headers(expectedHeaders);
    }
}


