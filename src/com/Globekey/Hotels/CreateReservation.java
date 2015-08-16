package com.Globekey.Hotels;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreateReservation {
	
	WebDriver driver = new FirefoxDriver();
	String appUrl = "https://www.globekey.com/reserve.php?hid=BKK322";	
	
	// @Test
	public void book() throws InterruptedException {
		
		driver.get(appUrl);
		driver.manage().window().maximize();
		Select droplist1 = new Select(driver.findElement(By.name("fm")));
		droplist1.selectByValue("8");
		Select droplist2 = new Select(driver.findElement(By.name("fd")));
		droplist2.selectByValue("12");
		Select droplist3 = new Select(driver.findElement(By.name("tm")));
		droplist3.selectByValue("8");
		Select droplist4 = new Select(driver.findElement(By.name("td")));
		droplist4.selectByValue("14");
		driver.findElement(By.className("gkButton")).click();
		driver.findElement(By.id("gkButton_2")).click();
		Select droplist5 = new Select(driver.findElement(By.name("adults")));
		droplist5.selectByValue("2");
		Select droplist6 = new Select(driver.findElement(By.name("child")));
		droplist6.selectByValue("2");
		Select droplist7 = new Select(driver.findElement(By.name("numrooms")));
		droplist7.selectByValue("2");
		driver.findElement(By.xpath(".//*[@id='gkOptionalExtrasRoom']/table/tbody/tr[1]/td[2]/input")).sendKeys("1");
		driver.findElement(By.xpath(".//*[@id='gkOptionalExtrasRoom']/table/tbody/tr[2]/td[2]/input[2]")).click();
		driver.findElement(By.name("opt[13269_18655_0]")).sendKeys("1");
		driver.findElement(By.className("gkButton")).click();
		Thread.sleep(1000);
		Select droplist8 = new Select(driver.findElement(By.name("custtitle")));
		droplist8.selectByValue("Mr.");
		driver.findElement(By.name("custfirstname")).sendKeys("Deepak");
		driver.findElement(By.name("custlastname")).sendKeys("Bansal");
		driver.findElement(By.name("custemail")).sendKeys("deepakb@erevmax.com");
		driver.findElement(By.name("custtel")).sendKeys("919038000810");
		driver.findElement(By.name("custaddress")).sendKeys("UTSA The Condoville");
		driver.findElement(By.name("custcity")).sendKeys("Kolkata");
		driver.findElement(By.name("arrdetails")).sendKeys("AI-1234");
		driver.findElement(By.id("gkArrival")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.findElement(By.name("comments")).sendKeys("This is a test reservation...!!!");
		Select droplist9 = new Select (driver.findElement(By.name("cardtype")));
		droplist9.selectByValue("Visa");
		driver.findElement(By.name("nameoncard")).sendKeys("Deepak Bansal");
		driver.findElement(By.name("cardnumber")).sendKeys("4012888888881881");
		Select droplist10 = new Select(driver.findElement(By.name("expirymonth")));
		droplist10.selectByValue("7");
		Select droplist11 = new Select(driver.findElement(By.name("expiryyear")));
		droplist11.selectByValue("2015");
		driver.findElement(By.name("credit_csc")).sendKeys("123");
		driver.findElement(By.id("gkBilladdress")).click();
		driver.findElement(By.name("policy_read")).click();
		driver.findElement(By.xpath(".//*[@id='gkPageWrapper']/div/form/ul/li[3]/div/fieldset/ul/li/div/fieldset/p[4]/button")).click();
		WebElement PageContent1 = driver.findElement(By.cssSelector(".gkReset.gkBookingConfirmation>h2"));
		System.out.println("" + PageContent1.getText());
		WebElement PageContent2 = driver.findElement(By.cssSelector(".gkPrintable>h2>span"));
		System.out.println("" + PageContent2.getText());
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		new CreateReservation().book();
	}
}