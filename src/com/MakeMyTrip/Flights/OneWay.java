package com.MakeMyTrip.Flights;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OneWay {
	
	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver, 30);
	WebElement datePicker;
	List<WebElement> noOfColumns;
	List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	
	//Expected Date, Month and Year
	int expMonth;
	int expYear;
	String expDate = null;
	
	//Calendar Month and Year
	String calMonth = null;
	String calYear = null;
	boolean dateNotFound;
	
	//@Test
	public void makemytrip() throws InterruptedException{
		
		driver.get("http://www.makemytrip.com/");
		driver.manage().window().maximize();
				
		driver.findElement(By.id("one_way_button1")).click();
		driver.findElement(By.id("from_typeahead1")).sendKeys("Kolkata");
		driver.findElement(By.id("to_typeahead1")).sendKeys("New Delhi");
		
		//Click on date text box to open date picker pop-up
		driver.findElement(By.id("start_date_sec")).click();
		dateNotFound = true;
		
		//Set your expected date, month and year
		expDate = "11";
		expMonth = 11;
		expYear = 2015;
	
		//This loop will be executed continuously till dateNotFound is true
		while(dateNotFound)
		{ 
			//Retrieve current selected month name from date picker pop-up
			calMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
			//Retrieve current selected year name from date picker pop-up
			calYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		   
			//If current selected month and year are same as expected month and year then go inside this condition
			if(monthList.indexOf(calMonth)+1 == expMonth && (expYear == Integer.parseInt(calYear)))
			{
				//Call selectDate function with date to select and set dateNotFound flag to false
				selectDate(expDate);
				dateNotFound = false;
			}
			//If current selected month and year are less than expected month and year then go inside this condition
			else if(monthList.indexOf(calMonth)+1 < expMonth && (expYear == Integer.parseInt(calYear)) || expYear > Integer.parseInt(calYear))
		   	{
				//Click on next button of date picker
				driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-e")).click();
		   	}
			//If current selected month and year are greater than expected month and year then go inside this condition
			else if(monthList.indexOf(calMonth)+1 > expMonth && (expYear == Integer.parseInt(calYear)) || expYear < Integer.parseInt(calYear))
			{
				//Click on previous button of date picker
				driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-w")).click();
			}
		}
		//Thread.sleep(3000);
		
		driver.findElement(By.id("flights_submit")).click();
		
		String actualTitle = driver.getTitle();
		wait.until(ExpectedConditions.titleIs(actualTitle));
		System.out.println("Now you are onto result page of " + actualTitle + "...");
		
		System.out.println("\nFlight: " + driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div[3]/div[2]/div[5]/div/div[1]/span[3]")).getText());
		System.out.println(driver.findElement(By.id("departure")).getText() + ": " + driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div[3]/div[2]/div[5]/div/div[3]/span[1]")).getText());
		System.out.println(driver.findElement(By.id("arrival")).getText() + ": " + driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div[3]/div[2]/div[5]/div/div[4]/span[1]")).getText());
		System.out.println(driver.findElement(By.id("price")).getText() + ": " + driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div[3]/div[2]/div[5]/div/div[7]/p[1]")).getText());
	}
	
	public void selectDate(String date){
		  datePicker = driver.findElement(By.id("ui-datepicker-div")); 
		  noOfColumns = datePicker.findElements(By.tagName("td"));

		  //Loop will rotate till expected date not found
		  for (WebElement cell: noOfColumns){
			  //Select the date from date picker when condition match
			  if (cell.getText().equals(date)){
				  cell.findElement(By.linkText(date)).click();
				  break;
			  }
		  }
	}
	
	public static void main(String[] args) throws InterruptedException{
		new OneWay().makemytrip();
	}
}