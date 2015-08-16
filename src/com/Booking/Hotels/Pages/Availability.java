package com.Booking.Hotels.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Availability {
	WebDriver driver;
	By checkInMonth = By.name("checkin_year_month");
	By checkInDate = By.name("checkin_monthday");
	By numberOfNights = By.name("interval");
	By checkAvailability = By.name("check");
	
	public Availability(WebDriver driver){
		this.driver = driver;
	}
	
	//Select Check-in Month
	public void selectCheckInMonth(String month){
		Select dropList = new Select(driver.findElement(checkInMonth));
		dropList.selectByValue(month);
	}
	
	//Select Check-in Date
	public void selectCheckInDate(String date){
		Select dropList = new Select(driver.findElement(checkInDate));
		dropList.selectByValue(date);
	}
	
	//Select Number of Nights
	public void selectNumberOfNights(String Nights){
		Select dropList = new Select(driver.findElement(numberOfNights));
		dropList.selectByVisibleText(Nights);
	}
	
	//Click on check availability button
	public void clickCheckAvailability(){
		driver.findElement(checkAvailability).click();
	}
	
	//This POM method will be exposed in test case to check availability of a room for a particular date and selected number of nights
	public void selectMonthDateNights(String month, String date, String Nights){
		//Select Check-in Month
		this.selectCheckInMonth(month);
		//Select Check-in Date
		this.selectCheckInDate(date);
		//Select Number of Nights
		this.selectNumberOfNights(Nights);
		//Click on check availability button
		this.clickCheckAvailability();
	}
}