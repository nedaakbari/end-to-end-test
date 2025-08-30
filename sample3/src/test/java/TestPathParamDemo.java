import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

public class TestPathParamDemo {
    //https://api.github.com/repos/andrejs-ps/playwright-java-starter
//    https://api.github.com/repos/andrejs-ps/Getting-Started-With-TestNG
    //https://github.com/andrejs-ps

    static final String REPO_EP = "https://api.github.com/repos";

    @Test
    void Path() {
        RestAssured.get(REPO_EP + "/andrejs-ps/playwright-java-starter")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(915030415));

        RestAssured.get(REPO_EP + "/andrejs-ps/Getting-Started-With-TestNG")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(227642020));

        RestAssured.get(String.format("https://api.github.com/repos/%S/%s", "andrejs-ps", "playwright-java-starter"))
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(227642020));

        RestAssured.get("https://api.github.com/repos/{user}/{repo}", "andrejs-ps", "playwright-java-starter")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(227642020));
    }

    @Test
    void withParam() {
        //برای Query Parameters استفاده میشه.
        //یعنی اون چیزی که بعد از ? توی URL میاد.
        RestAssured.given()
                .param("page", 2)
                .param("limit", 10)
                .when()
                .get("/users");

//👉 این تبدیل میشه به:  /users?page=2&limit=10

        //pathParam()
        //برای Path Variables استفاده میشه.
        //یعنی اون قسمتی از آدرس که داخل {} قرار می‌گیره.
        RestAssured.given()
                .pathParam("user", "andrejs-ps")
                .pathParam("repo", "playwright-java-starter")
                .when()
                .get("/repos/{user}/{repo}");
//👉 این تبدیل میشه به
        ///repos/andrejs-ps/playwright-java-starter

        RestAssured
                .given()
                .pathParam("user", "andrejs-ps")
                .pathParam("repo_name", "playwright-java-starter")
                .get(REPO_EP+"/{user}/{repo_name}")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(915030415));

        Map<String, String> map = Map.of("user", "andrejs-ps"
                , "repo_name", "playwright-java-starter");
        RestAssured
                .given()
                .pathParams(map)
                .get(REPO_EP+"/{user}/{repo_name}")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(915030415));
    }
}
