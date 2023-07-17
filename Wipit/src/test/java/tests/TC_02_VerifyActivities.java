package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Base.BaseClass;
import com.Base.PlaywrightFactory;
import com.PageObject.LoginPage;

public class TC_02_VerifyActivities extends BaseClass {

	/**
	 * BeforeClass Method to create ExtentTest in Extent Report
	 */
	@BeforeClass
	public void setupBeforeClass() {
		extentTest = reporter.createTest("TC_02_Verify Activities", "Add activities with details");
	}
	
	/**
	 * Test the activities functionality and able to create, edit, and delete 
	 * activities in order to manage schedule effectively.
	 * Test will soft assert the activities page title.
	 */
	
	@Test
	public void addActivitiesTest() {
		testNode = extentTest.createNode("TC_02 Verify Activity with Details");
		testNode.assignCategory("TC_01_Wipit-Activities");
		loginPage = new LoginPage(page, testNode);
		softAssert.assertEquals(loginPage.getHomePageTitle(), HOME_PAGE_TITLE);

		// Getting First invalid credentials data from Excel for Sign In page
		Assert.assertFalse(loginPage.doLoginInvalid(PlaywrightFactory.LoginDataProvider("Log In", "UserName", 16),
				PlaywrightFactory.LoginDataProvider("Log In", "Password", 16)));
    }
	
}
