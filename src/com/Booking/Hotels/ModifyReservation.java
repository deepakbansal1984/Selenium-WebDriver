package com.Booking.Hotels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModifyReservation {

	WebDriver driver = new FirefoxDriver();
	String baseURL = "https://secure.bookings.net/confirmation.en-us.html?sid=80a6af73b67d60be0bc7b8c2e934de12;dcid=2;bn=346015629;bp_travel_purpose=leisure;hostname=www.booking.com;source=book;token=0a455fadd9982a77416cb48cb82877878dedc34fc350f517ee4d876bda275f39;ua_created=0&";
		
	//@Test
	public void modify() throws InterruptedException{
		
		driver.get(baseURL);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement pageContent2 = driver.findElement(By.xpath(".//*[@id='book_conf_summary']/table/tbody/tr/td[1]/table[1]/tbody/tr[2]/th"));
		WebElement pageContent3 = driver.findElement(By.xpath(".//*[@id='book_conf_summary']/table/tbody/tr/td[1]/table[1]/tbody/tr[2]/td"));
		String bookingNumber = pageContent3.getText();
		bookingNumber = bookingNumber.replace(".", "");
		System.out.println("" + pageContent2.getText() + "-" + bookingNumber);
		
		WebElement pageContent4 = driver.findElement(By.xpath(".//*[@id='book_conf_summary']/table/tbody/tr/td[1]/table[1]/tbody/tr[3]/th"));
		WebElement pageContent5 = driver.findElement(By.xpath(".//*[@id='book_conf_summary']/table/tbody/tr/td[1]/table[1]/tbody/tr[3]/td"));
		String pinCode = pageContent5.getText();
		System.out.println("\n" + pageContent4.getText() + "-" + pinCode);
		
		//driver.findElement(By.id("close_signup_overlay")).click();
		
		driver.findElement(By.xpath(".//*[@id='user_form']/ul/li[8]/a")).click();
		String manageBookingWindow = driver.getWindowHandle();
		driver.switchTo().window(manageBookingWindow);
		driver.findElement(By.name("bn")).sendKeys(bookingNumber);
		driver.findElement(By.name("pincode")).sendKeys(pinCode);
		driver.findElement(By.cssSelector(".bootstrapped-input.btn.btn-primary.item-right")).click();
		String actualTitle = driver.getTitle();
		System.out.println("\nNow you are into " + actualTitle + " page where you can modify your reservation");
		
		wait.until(ExpectedConditions.titleIs(actualTitle));
		
		System.out.println("\n1) Case: Let's try to change the stay dates...");
		String mainWindow = driver.getWindowHandle(); //get the current window handle
		driver.findElement(By.cssSelector(".mb-btn.mb-change-dates.custom_track.hasSlideBox")).click(); //click some link that opens a new window
		
		for(String changeDatesWindow : driver.getWindowHandles()){
			driver.switchTo().window(changeDatesWindow); //switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		
		//code to do something on new window
		System.out.println("\nOutput: " + driver.findElement(By.xpath(".//*[@id='slidebox-change-dates-unavailable']/div/div[1]")).getText());
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='slidebox-change-dates-unavailable']/div/div[2]/input")));
		Thread.sleep(10000);
		driver.findElement(By.xpath(".//*[@id='slidebox-change-dates-unavailable']/div/div[2]/input")).click();
		
		driver.switchTo().window(mainWindow); //switch back to the original window
		Thread.sleep(10000);
		
		System.out.println("\n2) Case: Let's try to change the guest name...");
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mb-btn.mb-change-info.custom_track.hasSlideBox")));
		driver.findElement(By.cssSelector(".mb-btn.mb-change-info.custom_track.hasSlideBox")).click();
		
		for(String editGuestDetailsWindow : driver.getWindowHandles()){
			driver.switchTo().window(editGuestDetailsWindow);
		}
		
		driver.findElement(By.xpath(".//*[@id='slidebox-change-guest-details-709695660']/div/form/fieldset/div[1]/label[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='slidebox-change-guest-details-709695660']/div/form/fieldset/div[1]/label[1]/input")).sendKeys("Saumen Das");
		driver.findElement(By.xpath(".//*[@id='slidebox-change-guest-details-709695660']/div/form/fieldset/div[2]/input[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='slidebox-change-guest-details-709695660']/div/form/fieldset/div[2]/input[2]")));
		driver.findElement(By.xpath(".//*[@id='slidebox-change-guest-details-709695660']/div/form/fieldset/div[2]/input[2]")).click();
		
		for (String editGuestDetailsConfirmation : driver.getWindowHandles()){
			driver.switchTo().window(editGuestDetailsConfirmation);
		}
		
		Thread.sleep(5000);
		System.out.println("\nOutput: " + driver.findElement(By.xpath(".//*[@id='slidebox-change-guest-details-709695660']/div/fieldset/div[1]/p")).getText());
		driver.findElement(By.xpath(".//*[@id='slidebox-change-guest-details-709695660']/div/fieldset/div[2]/input")).click();
		
		driver.switchTo().window(mainWindow);
		
		System.out.println("\n3) Case: Let's try to change the room now...");
		driver.findElement(By.cssSelector(".mb-btn.mb-change-room.custom_track.hasSlideBox")).click();
		
		for (String changeYourRoomWindow : driver.getWindowHandles()){
			driver.switchTo().window(changeYourRoomWindow);
		}
		
		Thread.sleep(5000);
		System.out.println("\nOutput: " + driver.findElement(By.cssSelector(".fieldline.goal-no-availability")).getText());
		driver.close();
		
		driver.switchTo().window(mainWindow);
		
		System.out.println("\n4) Case: Let's add 1 more room in the same booking...");
		driver.findElement(By.cssSelector(".mb-ico.mb-ico--add-room")).click();
		
		for (String addAnotherRoomWindow : driver.getWindowHandles()){
			driver.switchTo().window(addAnotherRoomWindow);
		}
		
		Thread.sleep(5000);
		Select numberOfGuests = new Select(driver.findElement(By.name("nr_guests")));
		numberOfGuests.selectByValue("2");
		
	}
		
	public static void main(String[] args) throws InterruptedException{
		new ModifyReservation().modify();
	}
}