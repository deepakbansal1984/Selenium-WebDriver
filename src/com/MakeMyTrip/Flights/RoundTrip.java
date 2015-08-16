package com.MakeMyTrip.Flights;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RoundTrip {
	
	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver, 30);
	WebElement datePicker;
	List<WebElement> noOfColumns;
	List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	
	//@Test
	public void makemytrip() throws InterruptedException {
		
		//Expected Date, Month and Year
		int expDepMonth, expRetMonth;
		int expDepYear, expRetYear;
		String expDepDate, expRetDate = null;
		
		//Set number of adult, child and infant
		int adult, child, infant;
		int a, c, i;
		
		driver.get("http://www.makemytrip.com/");
		driver.manage().window().maximize();
		
		//driver.findElement(By.cssSelector(".flgh_pic.tab_icn")).click();
		//driver.findElement(By.cssSelector(".row.segmented_btn.first.active")).click();
		//driver.findElement(By.id("round_trip_button1")).click();
		//driver.findElement(By.id("one_way_button1")).click();
		driver.findElement(By.id("from_typeahead1")).clear();
		driver.findElement(By.id("from_typeahead1")).sendKeys("Chennai");
		driver.findElement(By.id("to_typeahead1")).clear();
		driver.findElement(By.id("to_typeahead1")).sendKeys("Port Blair");
		
		//Set your expected Departure date, month and year
		expDepDate = "15";
		expDepMonth = 10;
		expDepYear = 2015;
		
		//Click on date text box to open date picker pop-up
		driver.findElement(By.id("start_date_sec")).click();
		datePicker(expDepDate, expDepMonth, expDepYear);
		
		//Set your expected Return date, month and year
		expRetDate = "26";
		expRetMonth = 11;
		expRetYear = 2015;
		
		//Click on date text box to open date picker pop-up
		driver.findElement(By.id("return_date_sec")).click();
		datePicker(expRetDate, expRetMonth, expRetYear);
		
		//Select number of adult, child and infant
		adult = 1;
		child = 0;
		infant = 1;
		
		for (a=1; a!=adult; a++){
			driver.findElement(By.xpath(".//*[@id='adult_count']/a[2]")).click();
		}
		System.out.println("Number of " + driver.findElement(By.xpath(".//*[@id='widget_row']/div[1]/div/div[3]/div/div[8]/div[1]/div[1]/div/p")).getText() + " " + a);
		
		for (c=0; c!=child; c++){
			driver.findElement(By.xpath(".//*[@id='child_count']/a[2]")).click();
		}
		System.out.println("Number of " + driver.findElement(By.xpath(".//*[@id='widget_row']/div[1]/div/div[3]/div/div[8]/div[1]/div[2]/div/p")).getText() + " " + c);
		
		for (i=0; i!=infant; i++){
			driver.findElement(By.xpath(".//*[@id='infant_count']/a[2]")).click();
		}
		System.out.println("Number of " + driver.findElement(By.xpath(".//*[@id='widget_row']/div[1]/div/div[3]/div/div[8]/div[1]/div[3]/div/p")).getText() + " " + i);
		
		//Preparing a list of the available classes on the website and printing onto console
		WebElement flightClass = driver.findElement(By.id("class_selector"));
		List<WebElement> listOfFlightClass = flightClass.findElements(By.tagName("option"));
		System.out.println("\nAvailable " + driver.findElement(By.xpath(".//*[@id='widget_row']/div[1]/div/div[3]/div/div[8]/div[1]/div[4]/div/p")).getText());
		for(WebElement availableClasses : listOfFlightClass){
			System.out.println(availableClasses.getText());
		}
		
		//Selecting "Economy" class from the available 3 classes (E=Economy, PE=Premium Economy, B=Business)
		Select classList = new Select(driver.findElement(By.id("class_selector")));
		classList.selectByValue("E");
		System.out.println("\nSelected " + driver.findElement(By.xpath(".//*[@id='widget_row']/div[1]/div/div[3]/div/div[8]/div[1]/div[4]/div/p")).getText()
				+ " " + driver.findElement(By.className("economy_class")).getText());
				
		driver.findElement(By.id("flights_submit")).click();
		
		if (a < i){
			System.out.println("\n" + driver.findElement(By.cssSelector(".alert.alert-danger.warnings.warning_message>span")).getText());
			return;
		}else if (a + c > 9){
			System.out.println("\n" + driver.findElement(By.cssSelector(".alert.alert-danger.warnings.warning_message>span")).getText());
			return;
		}
		
		String actualTitle = driver.getTitle();
		wait.until(ExpectedConditions.titleIs(actualTitle));
		assertEquals("Flight Split Listing View", actualTitle);
		System.out.println("\nNow you are onto result page of " + actualTitle + "...");
				
		Thread.sleep(5000);	
		
		if (driver.findElements(By.cssSelector(".error_txt.append_bottom12")).size() != 0)
		{
		System.out.println("\n" + driver.findElement(By.cssSelector(".error_txt.append_bottom12")).getText());
		}
		else{
		
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".btn.btn-lg.pull-right.btn-primary-red"))));
			
			System.out.println("\n" + driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div/p/span[2]/span[2]")).getText());
			System.out.println("\nFlight: " + driver.findElement(By.xpath("//*[starts-with(@id,'dep-')]/div[1]/div[1]/span[2]/span[2]")).getText());
			System.out.println(driver.findElement(By.xpath(".//*[@id='departure']/span[1]")).getText() + ": " + driver.findElement(By.xpath("//*[starts-with(@id,'dep-')]/div[1]/div[2]/p[1]")).getText());
			System.out.println(driver.findElement(By.id("arrival")).getText() + ": " + driver.findElement(By.xpath("//*[starts-with(@id,'dep-')]/div[1]/div[3]/div/p[1]")).getText());
			System.out.println(driver.findElement(By.id("price")).getText() + ": " + driver.findElement(By.xpath("//*[starts-with(@id,'dep-')]/div[1]/div[4]/p[1]")).getText());
			
			System.out.println("\n" + driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div/p/span[2]/span[2]")).getText());
			System.out.println("\nFlight: " + driver.findElement(By.xpath("//*[starts-with(@id,'ret-')]/div[1]/div[1]/span[2]/span[2]")).getText());
			System.out.println(driver.findElement(By.xpath(".//*[@id='departure']/span[1]")).getText() + ": " + driver.findElement(By.xpath("//*[starts-with(@id,'ret-')]/div[1]/div[2]/p[1]")).getText());
			System.out.println(driver.findElement(By.id("arrival")).getText() + ": " + driver.findElement(By.xpath("//*[starts-with(@id,'ret-')]/div[1]/div[3]/div/p[1]")).getText());
			System.out.println(driver.findElement(By.id("price")).getText() + ": " + driver.findElement(By.xpath("//*[starts-with(@id,'ret-')]/div[1]/div[4]/p[1]")).getText());
			
			System.out.println("\n" + driver.findElement(By.xpath(".//*[@id='content']/div/div[2]/div/div/div[3]/div[3]/div/div[2]/div/p[1]")).getText() + ": " + driver.findElement(By.cssSelector(".stk_btm_price.text-right.spcl_prce.ng-binding")).getText());
		}
		
		driver.close();
		//driver.quit();
	}
	
	private void datePicker(String expDate, int expMonth, int expYear) {
				
		//Calendar Month and Year
		String calMonth = null;
		String calYear = null;
		boolean dateNotFound;
		
		dateNotFound = true;
		
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
	
	public static void main(String[] args) throws InterruptedException {
		new RoundTrip().makemytrip();
	}
}