package org.example.sample4.headers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class BasicResponseTest {

    @BeforeSuite
    public void beforeSuite() {
        RestAssured.baseURI = "https://api.github.com";
    }
    @Test
    void convenienceMethods() {
        Response response = RestAssured.get("/");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test
    void genericHeader() {
        Response response = RestAssured.get(baseURI);

        assertEquals(response.getHeader("server"), "github.com");
        assertEquals(response.getHeader("x-ratelimit-limit"), "60");
        // OR
        assertEquals(Integer.parseInt(response.getHeader("x-ratelimit-limit")), 60);
    }
}
