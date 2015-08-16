package com.eRevMax.CMLive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Login_Logout {

WebDriver driver=new FirefoxDriver();

@Test
public void login() throws InterruptedException
{
driver.get("https://postprod1.ratetiger.com:8101/#/login");
Thread.sleep(3000);
driver.findElement(By.cssSelector("div:nth-of-type(1)>.f12.ng-pristine.ng-invalid.ng-invalid-required")).sendKeys("deepakb");
driver.findElement(By.cssSelector("div:nth-of-type(2)>.f12.ng-pristine.ng-invalid.ng-invalid-required")).sendKeys("test");
driver.findElement(By.xpath("//input[@class='submit fb14']")).click();
}

@Test
public void logout() throws InterruptedException
{
	Thread.sleep(4000);
	driver.findElement(By.cssSelector(".icons.settings.fr.pointer.indent")).click();
	driver.findElement(By.xpath(".//*[@id='headerContent']/div[2]/ul/li[2]")).click();
}
}