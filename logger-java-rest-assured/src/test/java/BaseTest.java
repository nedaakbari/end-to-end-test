
import com.epam.reportportal.restassured.ReportPortalRestAssuredLoggingFilter;
import io.restassured.RestAssured;

public class BaseTest {
    static {
        RestAssured.filters(new ReportPortalRestAssuredLoggingFilter(42, LogLevel));
    }


}