package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductApiTest {

    @Test
    public void testGetProduct() {
        Response response = RestAssured.get("https://fakestoreapi.com/products/1");
        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.jsonPath().getString("title"));
    }
}
