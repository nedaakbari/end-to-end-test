package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductApiTest {

    @Test
    public void testGetProduct() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("Current thread: testGetProduct    " + Thread.currentThread().getName());
        Response response = RestAssured.get("https://fakestoreapi.com/products/1");
        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.jsonPath().getString("title"));
    }
}
