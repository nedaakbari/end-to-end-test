import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicResponseDemo {
    static final String BASE_URL = "https://api.github.com";

    @Test
    void response() {
        Response response = RestAssured.get(BASE_URL);
        ResponseBody body = response.getBody();
        int statusCode = response.getStatusCode();
        String contentType = response.getContentType();

        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(contentType,"application/json; charset=utf-8");
    }

}
