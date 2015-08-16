package com.ClearTrip.Flights;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Roundtrip {
	
	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver, 30);
	WebElement datePicker;
	List<WebElement> noOfColumns;
	List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

	//@Test
	public void cleartrip(){
		
		//Expected Date, Month and Year
		int expDepMonth, expRetMonth;
		int expDepYear, expRetYear;
		String expDepDate, expRetDate = null;
		
		//Set number of adults, children and infants
		int adults, children, infants;
		int a, c, i;
		
		driver.get("http://www.cleartrip.com/");
		driver.manage().window().maximize();
	}
	
}
