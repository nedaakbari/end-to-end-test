//package test;
//
//import io.qameta.allure.restassured.AllureRestAssured;
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//
//class TestMyWebsite {
//    ////Customize templates
//    static AllureRestAssured allureFilter = new AllureRestAssured()
//            .setRequestTemplate("my-http-request.ftl")
//            .setResponseTemplate("my-http-response.ftl");
//
//    @Test
//    void testSomeRequest() {
//        given()
//                .filter(allureFilter)
//                .get("https://jsonplaceholder.typicode.com/todos/1")
//                .then()
//                .body("userId", equalTo(1));
//    }
//}