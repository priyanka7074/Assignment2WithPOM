package com.makemytrip.qa.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.makemytrip.qa.base.TestBase;

public class LoginPage extends TestBase {

	private static final DateFormat sdf = new SimpleDateFormat("dd-MM-yyy");

	// Object repository

	@FindBy(id = "deny")
	WebElement notificationsBtn;

	@FindBy(xpath = "//span[@class='chNavIcon appendBottom2 chSprite chFlights active']")
	WebElement flightsTab;

	@FindBy(xpath = "//li[contains(text(),'Round Trip')]")
	WebElement roundtripBtn;

	@FindBy(id = "fromCity")
	WebElement fromField;

	@FindBy(xpath = "//input[@placeholder='From']")
	WebElement fromInputText;

	@FindBy(id = "toCity")
	WebElement toField;

	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement toInputText;

	@FindBy(xpath = "//span[contains(text(),'DEPARTURE')]")
	WebElement departField;
	
	@FindBy(xpath = "//a[contains(@class,'primaryBtn font24 latoBlack widgetSearchBtn')]")
	WebElement searchBtn;

	// Initializing the page objects
	public LoginPage() {
		PageFactory.initElements(driver, this); // this means pointing to the current class object

	}

	public SearchPage selectFromAndTo() {
		// notificationsBtn.click();
		Actions action = new Actions(driver);
		action.moveToElement(flightsTab).build().perform();
		flightsTab.click();
		action.moveToElement(roundtripBtn).build().perform();
		roundtripBtn.click();
		fromField.click();
		fromInputText.sendKeys("Delhi");
		fromInputText.click();
		toField.click();
		toInputText.sendKeys("Bangalore");
		toInputText.click();

		// Selecting calendar dates
		departField.click();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		String todayDate = sdf.format(cal.getTime());
		String todayDateArr[] = todayDate.split("-");
		String todayDay = todayDateArr[0];

		// Adding +7 days to the current date
		cal.add(Calendar.DATE, 7);
		String plusSevenDate = sdf.format(cal.getTime());
		String plusSevenDateArr[] = plusSevenDate.split("-");
		String plusSevenDay = plusSevenDateArr[0];

		// 20 xpath is below
		// div[@class='DayPicker-wrapper']//div[1]//div[3]//div[4 row]//div[5
		// col]//div[1]//p[1]
		String before_xpath = "//div[@class='DayPicker-wrapper']//div[1]//div[3]//div[";
		String middle_xpath = "]//div[";
		String after_xpath = "]//div[1]//p[1]";

		final int totalWeekDays = 7;

		boolean flag = false;
		String dayValue = null;

		for (int rowNum = 1; rowNum <totalWeekDays; rowNum++) {
			for (int colNum = 1; colNum <= totalWeekDays; colNum++) {

				try {
					dayValue = driver.findElement(By.xpath(before_xpath + rowNum + middle_xpath + colNum + after_xpath))
							.getText();
				} catch (NoSuchElementException e) {
					System.out.println("Please enter a correct date value");
					flag = false;
					break;
				}

				if (dayValue.equals(todayDay)) {

					driver.findElement(By.xpath(before_xpath + rowNum + middle_xpath + colNum + after_xpath)).click();
					break;
				}

				if (dayValue.equals(plusSevenDay)) {

					driver.findElement(By.xpath(before_xpath + rowNum + middle_xpath + colNum + after_xpath)).click();
					break;
				}

			}
			if (flag) {
				break;
			}

		}
		
		searchBtn.click();
		driver.manage().deleteAllCookies();
		return new SearchPage();

	}
}
