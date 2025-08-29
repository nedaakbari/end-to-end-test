import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.hamcrest.Matchers.*;

public class ValidatedResponseDemo {
    static final String BASE_URL = "https://api.github.com";

    @Test
    void response() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)//inseted of assert.equal
                .contentType(ContentType.JSON)
                .header("x-ratelimit-limit", "60");
    }

    @Test
    void response2() {
        // 1) headers(Map<String, ?> var1)
        RestAssured.get(BASE_URL)
                .then()
                .headers(
                        Map.of(
                                "x-ratelimit-limit", "60",
                                "x-ratelimit-remaining", "59"
                        )
                );

        // 2) headers(String var1, Object var2, Object... var3)
        RestAssured.get(BASE_URL)
                .then()
                .headers(
                        "x-ratelimit-limit", "60",
                        "x-ratelimit-remaining", "59"
                );

        // 3) header(String var1, Matcher<?> var2)
        RestAssured.get(BASE_URL)
                .then()
                .header("x-ratelimit-limit", Matchers.equalTo("60"));

        // 4) header(String var1, ResponseAwareMatcher<R> var2)
        RestAssured.get(BASE_URL)
                .then()
                .header("x-ratelimit-limit", response ->
                        Matchers.equalTo(response.header("x-ratelimit-limit"))
                );

        // 5) <V> header(String var1, Function<String, V> var2, Matcher<? super V> var3)
        RestAssured.get(BASE_URL)
                .then()
                .header(
                        "x-ratelimit-limit",
                        (Function<String, Integer>) Integer::parseInt, // تبدیل به int
                        Matchers.greaterThan(10) // انتظار داریم بیشتر از 10 باشه
                );

        // 6) header(String var1, String var2)
        RestAssured.get(BASE_URL)
                .then()
                .header("x-ratelimit-limit", "60");
    }

    @Test
    void response3() {
        RestAssured.get(BASE_URL)
                .then()
//                .statusCode(200)
                .statusCode(lessThan(300))
                .statusCode(anyOf(equalTo(200), equalTo(202)))
                .header("x-ratelimit-limit", notNullValue())
                .header("x-ratelimit-limit", Matchers.not(emptyString()))
                .header("x-ratelimit-limit", notNullValue())
                .header("x-ratelimit-limit", containsStringIgnoringCase("x"));
    }

    @Test
    void response4() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(lessThan(300))
                .header("cache-control", containsStringIgnoringCase("max-age=60"))
                .time(lessThan(3L), TimeUnit.SECONDS)
                .header("etag", notNullValue())
                .header("etags", is(emptyString()))
                .header("etag", not(emptyString()));

    }

    Map<String, String> expectedHeaders = Map.of(
            "content-encoding", "gzip",
            "access-control-allow-origin", "*");

    @Test
    void response5() {
        RestAssured.get(BASE_URL)
                .then()

                .headers("cache-control", containsStringIgnoringCase("public"),
                        "content-encoding", "gzip",
                        "access-control-allow-origin", "*")
                .headers(expectedHeaders);

    }
}
