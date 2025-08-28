package ir.mohaymen.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ATestApi2 {
    @Test
    void TestApi2() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++ start ATestApi2 TestApi2");
        baseURI = "http://localhost:3000";
        given().get("/users")
                .then().statusCode(400)
                .log()
                .all();
//                .body();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++ end ATestApi2 TestApi2");
    }

}
