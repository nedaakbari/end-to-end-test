//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class UserApiTest {
//
//    @Test
//    void getUserById() {
//        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/1");
//        assertEquals(200, response.getStatusCode());
//        System.out.println(response.jsonPath().getString("name"));
//    }
//}