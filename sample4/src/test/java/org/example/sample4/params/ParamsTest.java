package org.example.sample4.params;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.Map;

public class ParamsTest {

    static final String BASE_URL = "https://api.github.com/search/repositories";

    @Test
    void withParam() {
        RestAssured
                .given()
                    .param("q", "java")
                    .param("per_page", "1")
                .get(BASE_URL)
                .prettyPeek()
                .then()
                    .statusCode(200);
    }


    @Test
    void withParamAsMap() {
        RestAssured
                .given()
                    .params(Map.of("q", "java","per_page","1"))
                .get(BASE_URL)
                .prettyPeek()
                .then()
                    .statusCode(200);
    }
}
