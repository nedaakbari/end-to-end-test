package org.example.sample4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class Setup {

    public static RequestSpecification requestSpecification =
            new RequestSpecBuilder()
                    .setBaseUri("https://api.github.com/")
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();



    public static RequestSpecification requestSpecification2 =
            RestAssured
                    .given().
                    baseUri("https://api.github.com")
                    .contentType(ContentType.JSON);

    RequestSpecification spec = given()
            .baseUri("https://api.github.com")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .header("Authorization", "Basic TWNpOm1jaTEyMzQ1Njc4");
//            .param("status", "active");


    /// /////////////////////response
    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectContentType("application/json")
            .build();

    public static ResponseSpecification responseSpecification2 = expect()
            .statusCode(400)
            .contentType("application/json");
}
