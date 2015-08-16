package com.Booking.Hotels.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Booking.Hotels.Pages.Availability;

public class TestAvailability {
	WebDriver driver;
	Availability objAvail;
	
	@BeforeTest
	public void setup(){
		driver = new FirefoxDriver();
		driver.get("https://secure.bookings.net/book.html?test=11;hotel_id=11212");
		driver.manage().window().maximize();
	}
	
	//This test case will select a particular date, number of nights and click on the availability button
	@Test(priority=0)
	public void test_Month_Date_Nights_Selection(){
		//Create Availability object
		objAvail = new Availability(driver);
		
		//Enter the values for month, date and number of nights in the respective order
		objAvail.selectMonthDateNights("2015-12", "25", "2");
	}
}