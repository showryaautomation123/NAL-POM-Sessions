package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductComparisonpage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	public DriverFactory df;
	public WebDriver driver;
	protected LoginPage loginpage;
	protected Properties prop;
	protected AccountsPage accpage;
	protected SearchResultsPage searchResPage;
	protected ProductInfoPage productInfoPage;
	public SoftAssert softAssert;
	protected RegisterPage registerPage;
	protected ProductComparisonpage prodCompPage;
	 

	@BeforeTest
	public void setUp() {
		df=new DriverFactory();
		prop=df.initialize_prop();
		driver=df.init_driver(prop);
		loginpage=new LoginPage(driver);
		softAssert=new SoftAssert();
		
		
		
	}
	
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}