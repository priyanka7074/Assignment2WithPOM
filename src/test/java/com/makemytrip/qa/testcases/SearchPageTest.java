package com.makemytrip.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.makemytrip.qa.base.TestBase;
import com.makemytrip.qa.pages.LoginPage;
import com.makemytrip.qa.pages.SearchPage;

public class SearchPageTest extends TestBase {
	
	LoginPage loginpage;
	SearchPage searchpage;


	public SearchPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginpage = new LoginPage();
		searchpage = new SearchPage();
		searchpage = loginpage.selectFromAndTo();
	}
	
	
	@Test
	public void priceComparisonTest(){
		
		searchpage.selectFilter();
		String[] a = searchpage.priceComparison();
		Assert.assertEquals(a[0],a[2],"Depart flight prices are not matching");
		Assert.assertEquals(a[1],a[3],"Return flight prices are not matching");
		searchpage.getIntPriceValues();
		Assert.assertEquals((searchpage.intDepPrice1 + searchpage.intRetPrice1) ,searchpage.intTotPrice1,"Total fare price is not matching");		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
