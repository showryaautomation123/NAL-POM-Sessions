package com.qa.opencart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductComparisonpage;

public class ProductComparisonTest extends BaseTest {
	
	@BeforeClass
	public void productComparisonSetup() {
		loginpage.doLogIn(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		prodCompPage=new ProductComparisonpage(driver);
	}
	
	@DataProvider
	public Object[][] getProductSearchData() {
		return new Object[][] { { "Macbook", "MacBook Pro","Samsung","Samsung SyncMaster 941BW"},
			{ "Macbook", "MacBook Air","Samsung","Samsung Galaxy Tab 10.1"}};
	}
	
	@Test(dataProvider = "getProductSearchData")
	public void productComparisonTest(String searchKey, String productName, String searchKey1,
			String productName1) {
		ArrayList<String> productPricelist=new ArrayList<String>();
		productPricelist=prodCompPage.searchProduct( searchKey, productName,  searchKey1,
				productName1);
		softAssert.assertEquals(productPricelist.get(0),"$2,000.00");
		softAssert.assertEquals(productPricelist.get(1),"$242.00");
		softAssert.assertEquals(productPricelist.get(0),"$1,202.00");
		softAssert.assertEquals(productPricelist.get(1),"$241.99");
		softAssert.assertAll();
	}

}
