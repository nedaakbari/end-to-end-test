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

public class PlaylistTest {
    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    String access_token = "BQAOtlM-CSC8blrKhWC4AFXb7aYgrrBXwuIIWDgOBr7FNt9z6VbiuqGPPIHdEgP2wWnCj3gb7Olt9pWLXAEmB50QCC2ZniaoM8rmS37DqxvGVu6O3Nircrk4nHnZy3Id3YC6MNBFAOnFRGq7wS8gxwDGZ3cNMZohr-xpYzyJ57OLURbdl8a11r9uB3amicxsg4q4C5xYRthvuaLpIuNp-B_-r_lGYkEvvtEPXP4fNJQed6wv0f_iJAd_pneVjU1ekcEjAot8L-2JkfHRcgn9cw";
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
                //.expectContentType(ContentType.JSON)
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
                .when().post("/users/31blole434cqahr3jzhoygkatahi/playlists")
                .then().spec(responseSpecification)
                .assertThat().statusCode(201)
                .body("name", equalTo("New Playlist"),
                        "description", equalTo("New playlist description"),
                        "public", equalTo(false));
    }

    @Test
    public void ShouldBeAbleToGetAPlayList(){

        given()
                .spec(requestSpecification)
                .when()
                .get("/playlists/6yBp1uW6DD45tzIOFHnVck")
                .then()
                .spec(responseSpecification)
                .assertThat().statusCode(200)
                .body("name", equalTo("Updated Playlist Name"),
                        "description", equalTo("Updated playlist description"),
                        "public", equalTo(true));
    }

    @Test
    public void ShouldBeAbleToUpdateAPlayList(){
        String payload ="{\n" +
                "    \"name\": \"Updated Playlist Name\",\n" +
                "    \"description\": \"Updated playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .spec(requestSpecification)
                .body(payload)
                .when()
                .put("/playlists/6yBp1uW6DD45tzIOFHnVck")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(200);
    }

    /*Negative Scenario
    1. create a playlist without name 404
    2. Create a Playlist with Expired Token  401 Unauthorized.
     */


    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithoutName(){
        String payload ="{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .spec(requestSpecification)
                .body(payload)
                .when().post("/users/31blole434cqahr3jzhoygkatahi/playlists")
                .then().spec(responseSpecification)
                .assertThat().statusCode(400)
                .body("error.status", equalTo(400),
                        "error.message", equalTo("Missing required field: name"));
    }


    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithExpiredToken(){
        String payload ="{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given().
                baseUri("https://api.spotify.com")
                .header("Authorization", "Bearer " + "dummyvalue")
                .basePath("/v1")
                .contentType(ContentType.JSON)
                .log().all()
                .body(payload)
                .when().post("/users/31blole434cqahr3jzhoygkatahi/playlists")
                .then().spec(responseSpecification)
                .assertThat().statusCode(401)
                .body("error.status", equalTo(401),
                        "error.message", equalTo("Invalid access token"));
    }

}