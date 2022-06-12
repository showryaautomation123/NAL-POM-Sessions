package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RegisterTest extends BaseTest {

	@BeforeClass
	public void registerSetup() {
		registerPage = loginpage.goToRegisterPage();
	}

	@DataProvider
	public Object[][] getRegisterTestData() {
		return new Object[][] { { "sara", "sr","sara@gmail.com","919299467589","sara123","yes"}, 
			{ "tom", "tt" ,"tom1@gmail.com","9876897645","tom123","no"}, 
	{"tina","ta","tom1@gmail.com","8946537829","tom123","yes"}};
	}
//	public Object[][] getRegisterTestData() {
//		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
//		return regData;
//	}
//	
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "marchautomation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	

	@Test(dataProvider = "getRegisterTestData")
	public void registerUserTest(String firstname, String lastname,String email, 
							String telephone, String password, String subscribe) {
		Assert.assertTrue(
				
				registerPage.registerUser(firstname, lastname, email, telephone,  password,  subscribe)
				
				);
	}
	
	
	

}
