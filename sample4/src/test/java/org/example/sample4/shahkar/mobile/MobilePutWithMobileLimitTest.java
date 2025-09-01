package org.example.sample4.shahkar.mobile;

import com.google.gson.Gson;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.sample4.Setup;
import org.example.sample4.helper.requestModel.ShahkarRequestBody;
import org.example.sample4.helper.ShahkarRules;
import org.example.sample4.helper.ShahkarHelper;
import org.example.sample4.helper.requestModel.ShahkarResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MobilePutWithMobileLimitTest {//todo how much can i run this?

    @BeforeClass
    //    @BeforeMethod
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());
    }
//    Epic → Authentication
//    Feature → Login
//    Story → Login with valid credentials
//    Story → Login with invalid password
//    Feature → Registration
//    Story → Register with email
//    Story → Register with mobile number
@Epic("")
    @Test(description = "To get the result of post with for 10 time mobile service call ", priority = 1)
    @Story("POST Request with Valid post requestId")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Verify that the POST API returns correctly values")
    void iranianMobilePut() throws SQLException {
        int counter = 0;
        while (counter < ShahkarRules.iranianMobileNumber + 1) {
            ShahkarRequestBody iranianPerson = ShahkarHelper.getIranianPerson();
            Response response = given()
                    .when()
                    .spec(Setup.shahkarRequestSpec)
                    .body(new Gson().toJson(iranianPerson))
                    .post(baseURI + "/put").peek();


            ShahkarResponse shahkarResponse = response.as(ShahkarResponse.class);

            if (counter < ShahkarRules.iranianMobileNumber) {
                response.then()
                        .statusCode(200)
                        .body("response", equalTo(200))
                        .body("id", notNullValue())
                        .body("result", equalTo("OK."))
                        .body("requestId", equalTo(iranianPerson.getRequestId()))
                        .header("Content-Type", equalTo("application/json"));

            } else {
                response.then()
                        .statusCode(200)
                        .body("response", equalTo(601))
                        .body("$", hasKey("id"))
                        .body("id", nullValue())
                        .body("requestId", equalTo(iranianPerson.getRequestId()))
                        .header("Content-Type", equalTo("application/json"))
                        .body("result", equalTo("MaxAllowedNumberOfService;"));
            }
            counter++;
        }
    }
}
