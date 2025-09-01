import com.epam.reportportal.restassured.ReportPortalRestAssuredLoggingFilter;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

//https://github.com/reportportal/logger-java-rest-assured#readme
public class ApiTest {
        @BeforeSuite
        public void setupRestAssured() {
            RestAssured.filters(new ReportPortalRestAssuredLoggingFilter(42, LogLevel.INFO));
        }
    }