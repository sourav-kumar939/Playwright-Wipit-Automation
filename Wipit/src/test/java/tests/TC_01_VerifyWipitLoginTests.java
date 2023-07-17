package tests;

import static com.Base.PlaywrightFactory.takeScreenshot;
import static com.Utilities.ExtentReporter.extentLogWithScreenshot;
import static org.testng.Assert.assertFalse;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Base.BaseClass;
import com.Base.PlaywrightFactory;
//import com.PageObject.DashboardPage;
import com.PageObject.LoginPage;
import com.aventstack.extentreports.Status;

public class TC_01_VerifyWipitLoginTests extends BaseClass {

	/**
	 * BeforeClass Method to create ExtentTest in Extent Report
	 */
	@BeforeClass
	public void setupBeforeClass() {
		extentTest = reporter.createTest("TC_01_Verify Wipit Login Tests", "Verify login functionality of Wipit");
	}

	/**
	 * Test the login functionality of the application with valid credentials This
	 * test will soft assert the home page title and validate the login
	 */
	@Test(priority = 1)
	public void loginWithValidCredentialsTest() {
		testNode = extentTest.createNode("TC_01 Verify Wipit Login with Valid Credentials");
		testNode.assignCategory("TC_01_Wipit-Login");
		loginPage = new LoginPage(page, testNode);
		// softAssert.assertEquals(loginPage.getHomePageTitle(), HOME_PAGE_TITLE);
		// dashboardPage = loginPage.navigateToLoginPage();
		// Assert.assertTrue(loginPage.doLogin(testProperties.getProperty("username"),testProperties.getProperty("password")));
		loginPage.doLoginValid(testProperties.getProperty("username"), testProperties.getProperty("password"));
	}

//	@Test(priority = 2)
//	public void loginWithInvalidCredentialsTest() {
//		testNode = extentTest.createNode("TC_02 Verify Wipit Login with Invalid Credentials");
//		testNode.assignCategory("TC_01_Wipit-Login");
//		loginPage= new LoginPage(page, testNode);
//		softAssert.assertEquals(loginPage.getHomePageTitle(), HOME_PAGE_TITLE);
//		//loginPage = homePage.navigateToLoginPage();
//		//Assert.assertFalse(loginPage.doLogin(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 3), PlaywrightFactory.LoginDataProvider("Log In", "Password", 3)));
//		loginPage.doLogin1(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 3), PlaywrightFactory.LoginDataProvider("Log In", "Password", 3));
//	}

	@Test(priority = 2)
	public void loginWithInvalidCredentialsTest() {
		testNode = extentTest.createNode("TC_02 Verify Wipit Login with Invalid Credentials");
		testNode.assignCategory("TC_01_Wipit-Login");
		loginPage = new LoginPage(page, testNode);
		
		//Getting First invalid credentials data from Excel for Sign In page
		loginPage.loginEmail(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 3));
		loginPage.loginPassword(PlaywrightFactory.LoginDataProvider("Log In", "Password", 3));
		loginPage.loginButton();

		String screenshotPath = takeScreenshot(page);
		extentLogWithScreenshot(testNode, Status.FAIL, "User login to the Application is unsuccessful.", screenshotPath);
		
		//Getting second invalid credentials data from Excel for Sign In page
		loginPage.waiForLogInButton();
		loginPage.loginEmail("");
		loginPage.loginPassword("");
		
	}

}
