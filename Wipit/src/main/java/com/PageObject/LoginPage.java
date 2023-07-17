package com.PageObject;


import static com.Base.PlaywrightFactory.takeScreenshot;
import static com.Utilities.ExtentReporter.extentLog;
import static com.Utilities.ExtentReporter.extentLogWithScreenshot;


import java.util.Base64;

import com.Base.PlaywrightFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;



public class LoginPage {
	
	private Page page;
	private ExtentTest extentTest;

	// 1. String Locators - OR
	private String loginEmail = "//input[@type='email']";
	private String loginPassword = "//p[text()='Password']/following-sibling::input";
	private String loginButton = "//button[contains(@class , 'undefined large ') and text()='Log In']";
	private String waiForLogInButton = "//button[contains(@class , 'undefined large ') and contains(text(), '')]";
	//private String profileName = "//p[@id='header-profile-name' and contains(text(), '')]";
	private String dashboardPageTitle = "//title[text()='Dashboard - Wipit']";
//	private String errorTextDisplayed = "//small[@id='username2-help' and text()='Enter a valid email address']";
	private String errorTextDisplayed = "//div[@class='jsx-73a2175f9a80719f ic py-2 px-3 failure' and contains(text(), '')]";
	
	
	/**
     * Constructor to initialize the page objects with the {@link Page} instance and
     * {@link ExtentTest} instance
     * 
     * @param page       - {@link Page}
     * @param extentTest - {@link ExtentTest}
     */
	public LoginPage(Page page, ExtentTest extentTest) {
        this.page = page;
        this.extentTest = extentTest;
    }

	// 3. page actions/methods:
	public String getHomePageTitle() {
		String title =  page.title();
		//System.out.println("Page title: " + title);
		return title;
	}

	public String getHomePageURL() {
		String url =  page.url();
		System.out.println("Page url : " + url);
		return url;
	}

	public void loginEmail(String email) {
		page.fill(loginEmail, email);
		
	}
	
	public void loginPassword(String password) {
		page.fill(loginPassword, password);
		
	}
	
	public void loginButton() {
		//PlaywrightFactory.wairForElement(page, loginButton);
		page.click(loginButton);
		
	}
	
	public String errorTextDisplayed() {
		String errorText = page.locator(errorTextDisplayed).innerText();
		return errorText;
	}
	
	public void waiForLogInButton() {
//		String pleaseWait= page.locator(waiForLogInButton).innerText();
//		return pleaseWait;
		PlaywrightFactory.waitForElement(page, loginButton);
	}
		
//	public boolean doLogin(String appUserName, String appPassword) {
//        extentLog(extentTest, Status.INFO, "Login to Application using username " + appUserName);
//        page.fill(loginEmail, appUserName);
//        page.fill(loginPassword, new String(Base64.getDecoder().decode(appPassword)));
//        page.fill(loginPassword, appPassword);
//        page.click(loginButton);
//        if (page.locator(dashboardPageTitle).innerText().contains("Dashboard - Wipit")) {
//            extentLog(extentTest, Status.PASS, "User login to the Application successful.");
//        	//extentLogWithScreenshot(extentTest, Status.FAIL, "User login to the Application successful.", takeScreenshot(page));
//            return true;
//        }
//        
//        boolean isErrorDisplayed = false;
//        extentLogWithScreenshot(extentTest, Status.FAIL, "User login to the Application is unsuccessful.", takeScreenshot(page));
//        return !isErrorDisplayed;
//		
//    }
	
	public boolean doLoginValid(String appUserName, String appPassword) {
	    extentLog(extentTest, Status.INFO, "Login to Application using username " + appUserName);
	    extentLog(extentTest, Status.INFO, "Login to Application using password " + appPassword);
	    page.fill(loginEmail, appUserName);
	    page.fill(loginPassword, appPassword);
	    page.click(loginButton);

	    try {
	        // Check if the dashboard is displayed
	        page.locator(dashboardPageTitle).innerText().contains("Dashboard - Wipit");
	        extentLog(extentTest, Status.PASS, "User login to the Application successful.");
	        return true;
	    } catch (Exception e) {
	        extentLogWithScreenshot(extentTest, Status.FAIL, "User login to the Application is unsuccessful.", takeScreenshot(page));
	        return false;
	    }
	}
	
//	public boolean doLoginValid(String appUserName, String appPassword) {
//	    extentLog(extentTest, Status.INFO, "Login to Application using username " + appUserName);
//	    extentLog(extentTest, Status.INFO, "Login to Application using password " + appPassword);
//
//	    page.fill(loginEmail, appUserName);
//	    page.fill(loginPassword, appPassword);
//	    page.click(loginButton);
//
//	    // Check if the dashboard is displayed
//	    boolean isDashboardDisplayed = false;
//	    try {
//	        isDashboardDisplayed = page.locator(dashboardPageTitle).innerText().contains("Dashboard - Wipit");
//	    } catch (Exception e) {
//	        // Handle the exception if required
//	    }
//
//	    if (isDashboardDisplayed) {
//	        extentLog(extentTest, Status.PASS, "User login to the Application successful.");
//	        return true;
//	    } else {
//	        extentLogWithScreenshot(extentTest, Status.FAIL, "User login to the Application is unsuccessful.", takeScreenshot(page));
//	        return false;
//	    }
//	}
	
	public boolean doLoginInvalid(String appUserName, String appPassword) {
        extentLog(extentTest, Status.INFO, "Login to Application using username " + appUserName);
        extentLog(extentTest, Status.INFO, "Login to Application using password " + appPassword);
        page.fill(loginEmail, appUserName);
        page.fill(loginPassword, appPassword);
        page.click(loginButton);

        boolean isErrorDisplayed = page.textContent(errorTextDisplayed).contains("Invalid user credentials");
      //boolean isErrorDisplayed = page.locator(errorTextDisplayed).innerText().contains("Enter a valid email address");
        extentLogWithScreenshot(extentTest, Status.FAIL, "User login to the Application is unsuccessful.", takeScreenshot(page));
        return !isErrorDisplayed;
		
    }
	
//	public DashboardPage navigateToLoginPage() {
//		page.click(loginButton);
//		return new DashboardPage(page, extentTest);
//	}

}
