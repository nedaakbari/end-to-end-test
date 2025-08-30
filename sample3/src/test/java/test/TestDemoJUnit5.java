package test;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("REST API Regression Testing using JUnit5")
@Feature("Verify that the Get and POST API returns correctly")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDemoJUnit5 {

    @Test
    @Order(1)
    @Story("GET Request with Valid post id")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify that the GET API returns correctly")
    @DisplayName("To get the details of post with id 1")
    void verifyGetAPI() {
        given()
            .filter(new AllureRestAssured())
            .baseUri("https://jsonplaceholder.typicode.com")
            .header("Content-Type", "application/json")
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("userId", equalTo(1))
            .body("id", equalTo(1))
            .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
            .body("body", equalTo("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
    }

    @Test
    @Order(2)
    @Story("POST Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify that the post API returns correctly")
    @DisplayName("To create a new post")
    void verifyPostAPI() {
        given()
            .filter(new AllureRestAssured())
            .baseUri("https://jsonplaceholder.typicode.com")
            .header("Content-Type", "application/json")
        .when()
            .body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1\n}")
            .post("/posts")
        .then()
            .statusCode(201)
            .body("userId", equalTo(1))
            .body("id", equalTo(101))
            .body("title", equalTo("foo"))
            .body("body", equalTo("bar"));
    }
}
