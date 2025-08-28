package ir.mohaymen.test;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestOnLocalApi {
//این <test> خودش به یک thread اختصاص پیدا می‌کنه و داخل کلاس تست، تست‌ها پشت سر هم اجرا میشن
// مگر اینکه داخل کلاس از threadPoolSize استفاده کرده باشیم.
    @Test
//    @Test(threadPoolSize = 2, invocationCount = 2)
    void getUsers() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++start TestOnLocalApi  getUsers");
        baseURI = "http://localhost:3000";
        given().get("/users")
                .then().statusCode(200)
                .log()
                .all();
//                .body();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++end TestOnLocalApi  getUsers");
    }

    @Test
    void postUser() throws InterruptedException {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++start TestOnLocalApi postUser Thread sleep");
        JSONObject request = new JSONObject();
//        given().body("{\"username\":\"test\",\"password\":\"test\"}");
//        given().body("{\"username\":\"test\",\"password\":\"test\"}")
        request.put("id", "4");
        request.put("lastName", "gharaee");
        request.put("firstName", "sara");

        System.out.println(request.toString());
        System.out.println(request.toString());
        Thread.sleep(10000);
        baseURI = "http://localhost:3000";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/add-users")
                .then().statusCode(200).log().all();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++end TestOnLocalApi postUser Thread sleep");
    }
}
