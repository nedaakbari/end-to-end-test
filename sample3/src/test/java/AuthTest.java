import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {

    @Test
    public void basicAuthenticateSample() {
        given().auth()
                .basic("user1", "user1Pass")
                .when()
                .get("http://localhost:8080/spring-security-rest-basic-auth/api/foos/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
        //Preemptive Authentication
        //given().auth()
        //  .preemptive()
        //  .basic("user1", "user1Pass")
        //  .when()
        //  // ...
    }



//https://www.toolsqa.com/rest-assured/basic-auth/
    @Test
    public void getUserData() {
//        RequestSpecification httpRequest =  RestAssured.given().auth().preemptive().basic("postman", "password");

        given().auth().preemptive().basic("your username", "your password").get("your end point URL");
        //Using the preemptive directive of basic auth to send credentials to the server
//        given().auth().digest("your username", "your password").get("your end point URL")
//        given() .auth().form("your username", "your password").post("your end point URL")
//        given().auth().form("your username", "your password", new FormAuthConfig("/perform_signIn","user","password"))




        RequestSpecification httpRequest = given().auth().preemptive().basic("postman", "password");
        Response res = httpRequest.get("https://postman-echo.com/basic-auth");
        ResponseBody<?> body = res.body();
        //Converting the response body to string
        String rbdy = body.asString();
        System.out.println("Data from the GET API- " + rbdy);
    }

    @Test
    public void sample1() {
        RequestSpecification requestSpec;
        String baseURI = "http://qa3-phoenix.labcorp.com";
        String basePath = "/phx-rest/healthcheck/ping";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = baseURI;
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

        Response response = given()
                .spec(requestSpec)
                .auth().basic("username", "password")
                .when()
                .get(basePath)
                .then()
                .extract().response();
    }
}
