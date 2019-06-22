package com.makemytrip.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.makemytrip.qa.base.TestBase;

public class SearchPage extends TestBase {
	//calling these class variables in the assertion method of searchpagetest 
	String[] a = new String[5];
	public int intDepPrice1, intRetPrice1, intTotPrice1; 

	// Object repository
	@FindBy(xpath = "//div[@id='fli_filter__stops']//span[1]/label[1]/span[1]/span[1]")
	WebElement nonstopChkbx;

	@FindBy(xpath = "//div[@class='splitVw clearfix']/div[@id='ow_domrt-jrny']/div[2]/div[@class = 'fli-list splitVw-listing']")
	WebElement noOfDepartFlights;

	@FindBy(xpath = "//div[@class='splitVw clearfix']/div[@id='rt-domrt-jrny']/div[2]/div[@class = 'fli-list splitVw-listing']")
	WebElement noOfReturnFlights;

	@FindBy(xpath = "//div[@id='fli_filter__stops']//span[2]//label[1]//span[1]//span[1]")
	WebElement onestopChkbx;

	@FindBy(xpath = "//div[@id='ow_domrt-jrny']/div/div[2]/label[1]/div[1]/span[1]")
	WebElement departRadioBtn;

	@FindBy(xpath = "//div[@id='rt-domrt-jrny']/div/div[3]/label[1]/div[1]/span[1]")
	WebElement returnRadioBtn;

	@FindBy(xpath = "//div[@id='ow_domrt-jrny']/div/div[2]/label[1]/div[2]/div[3]/p")
	WebElement departFlightPrice;

	@FindBy(xpath = "//div[@id='rt-domrt-jrny']/div/div[3]/label[1]/div[2]/div[3]/p")
	WebElement returnFlightPrice;

	@FindBy(xpath = "//div[contains(@class,'splitVw-footer-left')]//p[@class='actual-price']")
	WebElement departFlightPrice1;

	@FindBy(xpath = "//div[contains(@class,'splitVw-footer-right')]//p[@class='actual-price']")
	WebElement returnFlightPrice1;
	
	@FindBy(xpath = "//span[@class='splitVw-total-fare']")
	WebElement totalFarePrice;
	
	

	// Initializing the page objects
	public SearchPage() {
		PageFactory.initElements(driver, this); // this means pointing to the current class object

	}

	public void selectFilter() {
		
		//Checking the no of flights returned after clicking nonstop checkbox
		nonstopChkbx.click();
		List<WebElement> departlist = driver.findElements(By.xpath(
				"//div[@class='splitVw clearfix']/div[@id='ow_domrt-jrny']/div[2]/div[@class = 'fli-list splitVw-listing']"));
		System.out.println("No of Depart flights for nonstop: " + departlist.size());
		List<WebElement> returnlist = driver.findElements(By.xpath(
				"//div[@class='splitVw clearfix']/div[@id='rt-domrt-jrny']/div[2]/div[@class = 'fli-list splitVw-listing']"));
		System.out.println("No of Return flights for nonstop: " + returnlist.size());
		nonstopChkbx.click();
        
		//Checking the no of flights returned after clicking oneStop checkbox
		onestopChkbx.click();
		List<WebElement> departlist1 = driver.findElements(By.xpath(
				"//div[@class='splitVw clearfix']/div[@id='ow_domrt-jrny']/div[2]/div[@class = 'fli-list splitVw-listing']"));
		System.out.println("No of Depart flights for onestop: " + departlist1.size());
		List<WebElement> returnlist1 = driver.findElements(By.xpath(
				"//div[@class='splitVw clearfix']/div[@id='rt-domrt-jrny']/div[2]/div[@class = 'fli-list splitVw-listing']"));
		System.out.println("No of Return flights for onestop: " + returnlist1.size());
		onestopChkbx.click();
        
		//Clicking on 2nd radio button from depart flights section
		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("arguments[0].click();", departRadioBtn);
        
		//Clicking on 3rd radio button from return flights section
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", returnRadioBtn);

	}

	public String[] priceComparison() {
		
        //created global String array a[] as it had to be accessed in the below getIntPriceValues method also
		//Getting the depart and return price values from both inner and outer frame and printing them on the console
		a[0] = departFlightPrice.getText();
		System.out.println("Depart Flight Price: " + a[0]);
		
		a[1] = returnFlightPrice.getText();
		System.out.println("Return Flight Price: " + a[1]);
		
		a[2] = departFlightPrice1.getText();
		System.out.println("Depart Flight Outer Price: " + a[2]);
		
		a[3] = returnFlightPrice1.getText();
		System.out.println("Return Flight Outer Price: " + a[3]);
		
		a[4] = totalFarePrice.getText();
		System.out.println("Total Fare Price: " + a[4]);
		
		return a;

	}
	
	public void getIntPriceValues() {
		
		//converting all the outer frame string price values to int and then comparing with the total price
		String depPrice1 = a[2].replaceAll("[^0-9]", "");
		intDepPrice1 = Integer.parseInt(depPrice1);
		System.out.println(intDepPrice1);
		
		String retPrice1 = a[3].replaceAll("[^0-9]", "");
		intRetPrice1 = Integer.parseInt(retPrice1);
		System.out.println(intRetPrice1);
		
		String totPrice1 = a[4].replaceAll("[^0-9]", "");
		intTotPrice1 = Integer.parseInt(totPrice1);
		System.out.println(intTotPrice1);
		
		
	}

}
