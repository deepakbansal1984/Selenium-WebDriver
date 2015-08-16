package com.Booking.Hotels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddAnotherRoom {
	
	WebDriver driver = new FirefoxDriver();
	String baseURL = "https://secure.booking.com/myreservations.en-us.html?bhc_csrf_token=Ns6oVQAAAAA%3DqJiF9jN1VMBADso_Mjb4mJ39PGNuPmtZV3CS9UKGF_NfW6KHSa4RLpYvWaFkx4xh_nfDiWCyXRE_rjKzVys9IWlPPXVCII8gMLT_3sOEVlCmaDfsI-6o1zSLn0aASyUd_Nb3G4HkmFW8KKP5WFD3m6hCb89t5detFgKpj-bFmJ_kifUYl0J9cB3_u0w&dcid=2&sid=80a6af73b67d60be0bc7b8c2e934de12&do=menu&from_form=1&bn=346015629&pincode=1129";
		
	//@Test
	public void modify() throws InterruptedException{
		
		driver.get(baseURL);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		System.out.println("\n4) Case: Let's add 1 more room in the same booking...");
		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.cssSelector(".mb-ico.mb-ico--add-room")).click();
		
		for (String selectNoOfGuestsWindow : driver.getWindowHandles()){
			driver.switchTo().window(selectNoOfGuestsWindow);
		}
		
		Thread.sleep(5000);
		WebElement numberOfGuests = driver.findElement(By.xpath(".//*[@id='slidebox-add-room']/div/form/fieldset/div[1]/label/select"));
		System.out.println("\nNumber of guests for additional room: " + numberOfGuests.getText());
			
		Select selectNumberOfGuests = new Select(driver.findElement(By.xpath(".//*[@id='slidebox-add-room']/div/form/fieldset/div[1]/label/select")));
		selectNumberOfGuests.selectByValue("2");
		driver.findElement(By.xpath(".//*[@id='slidebox-add-room']/div/form/fieldset/div[2]/input[2]")).click();
		
		for (String addAnotherRoomWindow : driver.getWindowHandles()){
			driver.switchTo().window(addAnotherRoomWindow);
		}
		
		Thread.sleep(5000);
		WebElement selectRoomType = driver.findElement(By.className("togglelink jqrt js-toggle-room-details js-default-action custom_track"));
		List<WebElement> listOfRooms = selectRoomType.findElements(By.className("togglelink jqrt js-toggle-room-details js-default-action custom_track"));
		System.out.println("\nTotal Room types available: " + listOfRooms.size());
				
		for (int i=2; i<=(2*listOfRooms.size()); i=i+2)	{
			WebElement roomType = driver.findElement(By.xpath(".//*[@id='mb-rooms-table']/tbody/tr[" + i + "]/td[2]/a"));
			System.out.println("\nRoom type: " + roomType.getText());
		}
}
	
	public static void main(String[] args) throws InterruptedException{
		new AddAnotherRoom().modify();
	}
	
}