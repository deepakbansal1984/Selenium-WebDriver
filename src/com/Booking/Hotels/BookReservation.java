package com.Booking.Hotels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BookReservation {

	WebDriver driver = new FirefoxDriver();
	String baseURL = "https://secure.bookings.net/book.html?test=11;hotel_id=11212";

	//@Test
	public void book() throws InterruptedException {
		
		driver.get(baseURL);
		driver.manage().window().maximize();
		
		Select dropList1 = new Select(driver.findElement(By.name("checkin_year_month")));
		dropList1.selectByValue("2015-12");
		Select dropList2 = new Select(driver.findElement(By.name("checkin_monthday")));
		dropList2.selectByValue("25");
		Select dropList3 = new Select(driver.findElement(By.name("interval")));
		dropList3.selectByVisibleText("2");
		driver.findElement(By.name("check")).click();
		
		WebElement selectRoomType = driver.findElement(By.className("chooseroom"));
		List<WebElement> listOfRooms = selectRoomType.findElements(By.tagName("a"));
		System.out.println("Total Room types available: " + listOfRooms.size());
				
		for (int i=2; i<=(2*listOfRooms.size()); i=i+2)	{
			WebElement roomName = driver.findElement(By.xpath(".//*[@id='bookwrapper']/tbody/tr/td/table/tbody/tr[" + i + "]/td[1]/a"));
			System.out.println("\nRoom Name: " + roomName.getText());
			
			WebElement chooseInventory = driver.findElement(By.xpath(".//*[@id='bookwrapper']/tbody/tr/td/table/tbody/tr[" + i + "]/td[4]/select"));
			List<WebElement> listOfInventory = chooseInventory.findElements(By.tagName("option"));
			for (WebElement noOfRooms : listOfInventory)
				System.out.println("No. of Rooms available: " + noOfRooms.getText());
			}
		
		Select chooseInventory = new Select(driver.findElement(By.xpath(".//*[@id='bookwrapper']/tbody/tr/td/table/tbody/tr[2]/td[4]/select")));
		chooseInventory.selectByIndex(1);
		driver.findElement(By.name("book")).click();
		Thread.sleep(15);
		
		Select dropList5 = new Select(driver.findElement(By.id("booker_title_field")));
		dropList5.selectByValue("mr");
		driver.findElement(By.id("firstname")).sendKeys("Deepak");
		driver.findElement(By.id("lastname")).sendKeys("Bansal");
		driver.findElement(By.id("email")).sendKeys("deepakb@erevmax.com");
		driver.findElement(By.id("email_confirm")).sendKeys("deepakb@erevmax.com");
		
		driver.findElement(By.id("bp_travel_purpose_leasure")).click();
		
		Select dropList6 = new Select(driver.findElement(By.cssSelector(".ClickTaleSensitive.bp_bs2_guest_dropdown.bp_input_select.notranslate")));
		dropList6.selectByValue("1");
		
		//driver.findElement(By.id("rentalcars_addon_1121212_81102251_0_2_0")).click();
		driver.findElement(By.id("addon_1121212_81102251_0_2_1_1")).click();
		Select dropList7 = new Select(driver.findElement(By.xpath("//select[contains(@name,'guests_addon_')]")));
		dropList7.selectByVisibleText("1");
		driver.findElement(By.xpath("//input[contains(@id,'addon_')]")).click();
		driver.findElement(By.xpath("//input[contains(@id,'addon_')]")).click();
		//driver.findElement(By.id("remarks")).sendKeys("This is a test reservation...!!!");
		driver.findElement(By.name("book")).click();
		
		driver.findElement(By.id("phone")).sendKeys("+91 (0) 903 800 0810");
		driver.findElement(By.name("book")).click();
		
		driver.findElement(By.id("close_signup_overlay")).click();
		
		WebElement pageContent1 = driver.findElement(By.id("b_conf_thanks"));
		System.out.println("" + pageContent1.getText());
		WebElement pageContent2 = driver.findElement(By.xpath(".//*[@id='book_conf_summary']/table/tbody/tr/td[1]/table[1]/tbody/tr[2]/th"));
		WebElement pageContent3 = driver.findElement(By.xpath(".//*[@id='book_conf_summary']/table/tbody/tr/td[1]/table[1]/tbody/tr[2]/td"));
		System.out.println("\n" + pageContent2.getText() + "-" + pageContent3.getText());
		WebElement pageContent4 = driver.findElement(By.xpath(".//*[@id='book_conf_summary']/table/tbody/tr/td[1]/table[1]/tbody/tr[3]/th"));
		WebElement pageContent5 = driver.findElement(By.xpath(".//*[@id='book_conf_summary']/table/tbody/tr/td[1]/table[1]/tbody/tr[3]/td"));
		System.out.println("\n" + pageContent4.getText() + "-" + pageContent5.getText());
		
	}
		
	public static void main(String[] args) throws InterruptedException {
		new BookReservation().book();
	}
}