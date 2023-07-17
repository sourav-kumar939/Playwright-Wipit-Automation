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
import com.microsoft.playwright.options.LoadState;

public class TC_01_VerifyWipitLoginTests2 extends BaseClass {

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
		Assert.assertTrue(
				loginPage.doLoginValid(testProperties.getProperty("username"), testProperties.getProperty("password")));
//		loginPage.doLogin(testProperties.getProperty("username"), testProperties.getProperty("password"));
	}

//	@Test(priority = 2)
//	public void loginWithInvalidCredentialsTest() {
//		testNode = extentTest.createNode("TC_02 Verify Wipit Login with Invalid Credentials");
//		testNode.assignCategory("TC_01_Wipit-Login");
//		loginPage= new LoginPage(page, testNode);
//		softAssert.assertEquals(loginPage.getHomePageTitle(), HOME_PAGE_TITLE);
//		//loginPage = homePage.navigateToLoginPage();
//		Assert.assertTrue(loginPage.doLogin(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 6), PlaywrightFactory.LoginDataProvider("Log In", "Password", 6)));
//		//loginPage.doLogin(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 3), PlaywrightFactory.LoginDataProvider("Log In", "Password", 3));
//	}

	@Test(priority = 2)
	public void loginWithInvalidCredentialsTest() {
		testNode = extentTest.createNode("TC_02 Verify Wipit Login with Invalid Credentials");
		testNode.assignCategory("TC_01_Wipit-Login");
		loginPage = new LoginPage(page, testNode);
		softAssert.assertEquals(loginPage.getHomePageTitle(), HOME_PAGE_TITLE);

		// Getting First invalid credentials data from Excel for Sign In page
		Assert.assertFalse(loginPage.doLoginInvalid(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 6),
				PlaywrightFactory.LoginDataProvider("Log In", "Password", 6)));

//		String screenshotPath = takeScreenshot(page);
//		extentLogWithScreenshot(testNode, Status.FAIL, "User login to the Application is unsuccessful.", screenshotPath);

		// Getting Second invalid credentials data from Excel for Sign In page
		loginPage.waiForLogInButton();
		loginPage.loginEmail("");
		loginPage.loginPassword("");
		page.waitForLoadState(LoadState.NETWORKIDLE);
		loginPage.waiForLogInButton();

		softAssert.assertEquals(loginPage.getHomePageTitle(), HOME_PAGE_TITLE);
		Assert.assertFalse(loginPage.doLoginInvalid(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 7),
				PlaywrightFactory.LoginDataProvider("Log In", "Password", 7)));

		// Getting Third invalid credentials data from Excel for Sign In page
		loginPage.waiForLogInButton();
		loginPage.loginEmail("");
		loginPage.loginPassword("");
		loginPage.waiForLogInButton();

		softAssert.assertEquals(loginPage.getHomePageTitle(), HOME_PAGE_TITLE);
		Assert.assertFalse(loginPage.doLoginInvalid(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 8),
				PlaywrightFactory.LoginDataProvider("Log In", "Password", 8)));
		
	}
	
	@Test(priority = 3)
	public void loginWithInactiveCredentialsTest() {
		testNode = extentTest.createNode("TC_03 Verify Wipit Login with Inactive Credentials");
		testNode.assignCategory("TC_01_Wipit-Login");
		loginPage = new LoginPage(page, testNode);
		softAssert.assertEquals(loginPage.getHomePageTitle(), HOME_PAGE_TITLE);

		// Getting First invalid credentials data from Excel for Sign In page
		Assert.assertFalse(loginPage.doLoginInvalid(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 16),
				PlaywrightFactory.LoginDataProvider("Log In", "Password", 16)));
    }
	
//	@Test(priority = 4)
//	public void simultaneouslySignInWithSameCredentialsInSameBrowser() {
//		testNode = extentTest.createNode("TC_03 Verify Wipit Login with Inactive Credentials");
//		testNode.assignCategory("TC_01_Wipit-Login");
//		loginPage = new LoginPage(page, testNode);
//		softAssert.assertEquals(loginPage.getHomePageTitle(), HOME_PAGE_TITLE);
//
//		// Getting First invalid credentials data from Excel for Sign In page
//		Assert.assertFalse(loginPage.doLoginInvalid(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 16),
//				PlaywrightFactory.LoginDataProvider("Log In", "Password", 16)));
//    }
	
	
	
	

}
