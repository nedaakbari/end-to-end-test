//package org.example;
//
//import org.junit.jupiter.api.Test;
//
//@Epic("Web Application Regression Testing")
//@Feature("Login Page Tests")
//@Listeners(TestExecutionListener.class)
//public class LoginTests extends BaseTest {
//
//	LoginPage objLogin;
//	DashboardPage objDashboardPage;
//
//	@Severity(SeverityLevel.NORMAL)
//	@Test(priority = 0, description = "Verify Login Page")
//	@Description("Test Description : Verify the title of Login Page")
//	@Story("Title of Login Page")
//	public void verifyLoginPage() {
//
//		// Create Login Page object
//		objLogin = new LoginPage(driver);
//
//		// Verify login page text
//		objLogin.verifyPageTitle();
//	}
//}