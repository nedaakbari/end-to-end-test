//package test;
//
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//
//public class PermissionTests {
//
//    private static RequestSpecification requestSpec;
//
//    @BeforeClass
//    public static void AuthSetup() {
//
//        Response body = given().log().all()
//                .params("username", "user@example.com", "password", "pass!", "grant_type", "password")
//                .post("https://test.example.com/Token").then().log().body().statusCode(200).extract().response();
//
//        String access_token = body.path("access_token").toString();
//        String token_type = body.path("token_type").toString();
//        String refresh_token = body.path("refresh_token").toString();
//        String Authorization = "bearer " + access_token;
//
//        RequestSpecBuilder builder = new RequestSpecBuilder();
//        builder.addHeader("officeId", "1");
//        builder.addHeader("organizationId", "1");
//        builder.addHeader("refresh_token", refresh_token);
//        builder.addHeader("Authorization", Authorization);
//
//        requestSpec = builder.build();
//
//    }
//
//    @Test
//    public void addNewGraph() {
//        given().spec(requestSpec).log().all().when().get("https://test.example.com/api/cases/recent").then().log()
//                .body().statusCode(200);
//    }
//}