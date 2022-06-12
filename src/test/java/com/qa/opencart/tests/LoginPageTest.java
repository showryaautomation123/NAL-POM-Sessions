package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

public class LoginPageTest extends BaseTest {

	@Test
	public void AloginPageTitleTest() {
		String actTitle=loginpage.getLoginPageTitle();
		System.out.println(actTitle);
		Assert.assertEquals(actTitle,Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginPageUrlTest() {
		String actUrl=loginpage.getLoginPageUrl();
		System.out.println(actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test
	public void ForgotPwdLnkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLnkExist());
	}
	
	@Test
	public void RegsterLnkExistTest() {
		Assert.assertTrue(loginpage.isRegsterLnkExist());
	}
	
	@Test
	public void loginTest(){
		Assert.assertTrue(loginpage.doLogIn(prop.getProperty("username").trim(), prop.getProperty("password").trim()).isLogoutLinkExist());
	}
	
}

