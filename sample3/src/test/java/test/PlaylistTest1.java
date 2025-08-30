package test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class PlaylistTest1 {
    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    String access_token = "BQA1z9PuzuUlZH0PIP4SydPOcWKsKh4OtamMCNNIhuy9f5e80wyM25VxCE4ZOJfOA1t3ijIwcThQUjTPD1tOnb3hH5H62DCtb-YC_A_6z9Qef3bH4YC3jdnaE3t-KW24PQ72B_h6Q3ZqQUDCWftI6YDoAdzDGbAp_LV_X5qNY6QzP-AgqTkE-H4Jimt3llVnU19YUJTOMewwJTyHT_UJfMwIP6iNXFUnSUjkJl1qFvNnCJFOBpRLueFZACRn0_2Xu7qDPlX-wuOM3fRWvb32EA";
    String refresh_token="";

    @BeforeClass
    public void beforeClass(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .addHeader("Authorization", "Bearer " + access_token)
                .setBasePath("/v1")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void ShouldBeAbleToCreateAPlayList(){
        String payload ="{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .spec(requestSpecification)
                .body(payload)
                .when()
                .post("/users/31blole434cqahr3jzhoygkatahi/playlists")
                .then()
                .spec(responseSpecification)
                .assertThat().statusCode(201)
                .body("name", equalTo("New Playlist"),
                        "description", equalTo("New playlist description"),
                        "public", equalTo(false));
    }
}