package com.makemytrip.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.makemytrip.qa.base.TestBase;
import com.makemytrip.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage loginpage;


	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginpage = new LoginPage();
	}
	
	@Test
	public void selectDestTest() {
		loginpage.selectFromAndTo();
		
	}
	

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
