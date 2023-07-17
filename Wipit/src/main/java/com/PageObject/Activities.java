package com.PageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

public class Activities {
	
	private Page page;
	private ExtentTest extentTest;
	private static Logger log = LogManager.getLogger();

	// 1. String Locators - OR
	private String quickAddButton = "//p[text()='Add']";
	private String activityDescription = "//textarea[@id='desc']";
	private String companyDropDown = "(//div[@id='wpi-company']//div)[1]";
	private String companySearchBox = "//div[@class='p-dropdown-filter-container']/input";
	private String companyDropdownOptions = "//div[@class='p-dropdown-items-wrapper']//li[contains(text(),'%s')]";
	private String contactDropDown = "(//div[@id='wpi-contact']//div)[1]";
	private String contactSearchBox = "//input[contains(@class,'p-dropdown-filter p-inputtext')]";
	private String contactDropdownOptions = "//div[@class='p-dropdown-items-wrapper']//li[contains(text(),'%s')]";
	private String dateOfActivity = "//span[@id='datepicker-date']//input[1]";
	private String selectDateOfActivityDatePicker = "//span[contains(text(),'%s')]";        //"//table[@class='p-datepicker-calendar']//tbody//td/span[contains(text(),'')]";
	private String datePickerYear = ".p-datepicker-year";
	private String datePickerMonth = ".p-datepicker-month";
	private String datePickerPreviewButton = ".p-datepicker-prev";
	private String datePickerNextButton = ".p-datepicker-next";
	private String activityType = "(//div[@id='wpi-type']//div)[1]";
	private String activityTypeDropdownOptions = "//div[@class='p-dropdown-items-wrapper']//li[contains(text(),'%s')]";
	
	
	
	/**
     * Constructor to initialize the page objects with the {@link Page} instance and
     * {@link ExtentTest} instance
     * 
     * @param page       - {@link Page}
     * @param extentTest - {@link ExtentTest}
     */
	public Activities(Page page, ExtentTest extentTest) {
        this.page = page;
        this.extentTest = extentTest;
    }
	
	/**
     * Method to get Login page title
     * 
     * @return String - Returns page title
     */
    public String getActivitiesPageTitle() {
        page.waitForLoadState();
        return page.title();
    }
    
    public void quickAddButton() {
    	page.click(quickAddButton);
    }
    
    public void activityDescription(String description) {
    	page.fill(activityDescription, description);
    }
    
    public void companyDropDown() {
    	page.click(companyDropDown);
    }
    
    public void companySearchBox(String searchBox) {
    	page.fill(companySearchBox, searchBox);
    }
    
    public String companyDropdownOptions(String dropdownOptions) {
    	String xpath = String.format(companyDropdownOptions, dropdownOptions);
    	page.fill(xpath, dropdownOptions);
		return xpath;
    }
    
    public void contactDropDown() {
    	page.click(contactDropDown);
    }
    
    public void contactSearchBox(String searchBox) {
    	page.fill(contactSearchBox, searchBox);
    }
    
    public String contactDropdownOptions(String dropdownOptions) {
    	String option = String.format(contactDropdownOptions, dropdownOptions);
    	page.fill(option, dropdownOptions);
		return option;
    } 
    
    public void dateOfActivity() {
    	page.click(dateOfActivity);
    }
    
    public String datePickerYear() {
    	String currentYear = page.innerText(datePickerYear).trim();
		return currentYear;
		
	}
    
    public String datePickerMonth() {
    	String currentMonth = page.innerText(datePickerMonth).trim();
		return currentMonth;
		
	}
    
    public void datePickerPreviewButton() {
    	page.click(datePickerPreviewButton);
    }
    
    public void datePickerNextButton() {
    	page.click(datePickerNextButton);
    }
    
    public String selectDateOfActivityDatePicker(String selectDate) {
    	String desiredDate = String.format(selectDateOfActivityDatePicker, selectDate);
    	page.fill(desiredDate, selectDate);
		return desiredDate;
    }
    
    public void activityType() {
    	page.click(activityType);
    }
    
    public String activityTypeDropdownOptions(String dropdownOptions) {
    	String options = String.format(activityTypeDropdownOptions, dropdownOptions);
    	page.fill(options, dropdownOptions);
		return options;
    }

}
