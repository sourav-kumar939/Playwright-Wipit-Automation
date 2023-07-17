package com.PageObject;

import static org.testng.Assert.assertTrue;

import java.time.Month;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.*;


public class Demo {

	@Test
	public void m1() throws InterruptedException {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Navigate to a web page
			page.navigate("https://staging.wipit.io/");

			page.fill("//input[@type='email']", "hiriy19715@gam1fy.com");
			page.fill("//p[text()='Password']/following-sibling::input", "Sourav123");
			page.click("(//button[text()='Log In'])[2]");

//            String str= "//div[@class='jsx-73a2175f9a80719f ic py-2 px-3 failure' and contains(text(), '')]";
//            if (page.locator(str).innerText().contains("Invalid user credentials")) {
//				System.out.println("Trueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
//			}
			String quickAddButton = "//div/button//p[text()='Add']";//"//p[text()='Add']";
			String companyDropDown = "(//div[@id='wpi-company']//div)[1]";
			String companySearchBox = "//div[@class='p-dropdown-filter-container']/input";
			String companyDropdownOptions = "//div[@class='p-dropdown-items-wrapper']//li[contains(text(),'')]";
			String datePicker = "//span[@id='datepicker-date']";

			page.click(quickAddButton);
//            page.click(companyDropDown);
//            page. fill(companySearchBox, "ABCD");
			page.click(datePicker);

			
			page.waitForSelector(".p-datepicker-title");
			
			

            // Get the current year and month
//            String currentYear = page.innerText(".p-datepicker-year").trim();
//            System.out.println("Current Year :"+currentYear);
//            String currentMonth = page.innerText(".p-datepicker-month").trim();
//            System.out.println("Current Month :"+currentMonth);
//
//            // Set the desired year and month
//            String desiredYear = "2024";
//            String desiredMonth = "July";
//
//            while (!currentYear.equals(desiredYear) || !currentMonth.equals(desiredMonth)) {
//                // Click on the next button to navigate to the next month
//                
//                if(currentMonth.equals(desiredMonth)) {
//                	System.out.println("Hellow");
//                }
//                page.waitForTimeout(500); // Wait for the calendar to update
//
//                // Update the current year and month
//                currentYear = page.innerText(".p-datepicker-year");
//                currentMonth = page.innerText(".p-datepicker-month");
//            }
			
			// Get the current year and month
			String currentYear = page.innerText(".p-datepicker-year").trim();
			System.out.println("Current Year: " + currentYear);
			String currentMonth = page.innerText(".p-datepicker-month").trim();
			System.out.println("Current Month: " + currentMonth);

			// Set the desired year and month
			String desiredYear = "2023";
			String desiredMonth = "August";
			int desiredDate = 10;

			while (!currentYear.equals(desiredYear) || !currentMonth.equals(desiredMonth)) {
			    // Check if the desired month and year are before the current month and year
			    int currentYearInt = Integer.parseInt(currentYear);
			    int desiredYearInt = Integer.parseInt(desiredYear);

			    int currentMonthInt = getMonthNumber(currentMonth);
			    int desiredMonthInt = getMonthNumber(desiredMonth);

			    if (desiredYearInt < currentYearInt || (desiredYearInt == currentYearInt && desiredMonthInt < currentMonthInt)) {
			        // Click on the previous button to navigate to the previous month
			        page.click(".p-datepicker-prev");
			       // page.waitForTimeout(500); // Wait for the calendar to update
			    } else {
			        // Click on the next button to navigate to the next month
			        page.click(".p-datepicker-next");
			      //  page.waitForTimeout(500); // Wait for the calendar to update
			    }

			    // Update the current year and month
			    currentYear = page.innerText(".p-datepicker-year").trim();
			    currentMonth = page.innerText(".p-datepicker-month").trim();
			}
			
//			page.click("//table[@class='p-datepicker-calendar']//tbody//td/span[contains(text(),'"+desiredDate+"')]");
			page.click("//span[contains(text(),'"+desiredDate+"')]");
			
			
			System.out.println("Souravvvvvvvvvvvvvvvvvvvvv");



			page.waitForTimeout(5000);

		}
	}
	
	// Helper method to get the month number from the month name
	private int getMonthNumber(String monthName) {
	    return Month.valueOf(monthName.toUpperCase()).getValue();
	}

	private static int getMonthIndex(String monthName) {
		switch (monthName.toLowerCase()) {
		case "January":
		case "jan":
			return 0;
		case "February":
		case "feb":
			return 1;
		case "March":
		case "mar":
			return 2;
		case "April":
		case "apr":
			return 3;
		case "May":
			return 4;
		case "June":
		case "jun":
			return 5;
		case "July":
		case "jul":
			return 6;
		case "August":
		case "aug":
			return 7;
		case "September":
		case "sep":
			return 8;
		case "October":
		case "oct":
			return 9;
		case "November":
		case "nov":
			return 10;
		case "December":
		case "dec":
			return 11;
		default:
			return -1;
		}
	}

}
