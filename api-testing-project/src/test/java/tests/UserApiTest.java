package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class UserApiTest {

    @DataProvider(name = "userData")
    public Object[][] userDataProvider() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("testData.json");
        List<Map<String, Object>> data = mapper.readValue(is, List.class);
        Object[][] result = new Object[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i).get("id");
            result[i][1] = data.get(i).get("name");
        }
        return result;
    }

    @Test(dataProvider = "userData")
    public void testGetUserById(int id, String expectedName) {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/" + id);
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getString("name"), expectedName);
    }
}
