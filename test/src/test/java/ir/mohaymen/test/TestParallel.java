package ir.mohaymen.test;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestParallel {
    @Test
    void getUsers2() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++start ParallTest TestOnLocalApi2");
        baseURI = "http://localhost:3000";
        given().get("/users")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"))
                .statusCode(200)
                .log()
                .all();
//                .body();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++end ParallTest TestOnLocalApi2");
    }
}
