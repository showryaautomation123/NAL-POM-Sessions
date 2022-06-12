package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. private By locators:
	private By emailid=By.id("input-email");
	private By password=By.id("input-password");
	private By loginbtn=By.xpath("//input[@value='Login']");
	private By forgotPasswordlnk=By.linkText("Forgotten Password");
	private By registerlnk=By.linkText("Register");
	private By logoutSuccessMsg=By.cssSelector("div#content h1");
	private By myaccount=By.xpath("//li[@class='dropdown']/a[@title='My Account'] ");
	private By login=By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Login')]");
	
	//2.page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		eleUtil.waitForElementVisible(myaccount,Constants.DEFAULT_ELEMENT_TIMEOUT).click();
		eleUtil.waitForElementVisible(login,Constants.DEFAULT_ELEMENT_TIMEOUT).click();
	}
	
	@Step("getting login page title of open cart app...")
	public String getLoginPageTitle() {	
		return eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}
	
	@Step("getting login page url of open cart app...")
	public String getLoginPageUrl() {
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}
	
	@Step("user is able to login with username: {0} and password: {1}")
	public AccountsPage doLogIn(String un,String pwd)  {
		eleUtil.waitForElementVisible(emailid, Constants.DEFAULT_ELEMENT_TIMEOUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);;
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}

	@Step("isForgotPwdLinkExist...")
	public boolean isForgotPwdLnkExist() {
		return eleUtil.isDisplayed(forgotPasswordlnk);
	}
	
	@Step("isRegisterLinkExist...")
	public boolean isRegsterLnkExist() {
		return eleUtil.isDisplayed(registerlnk);
	}
	
	@Step("fetching success messg for logout...")
	public String getLogoutSuccessMsg() {
		return eleUtil.waitForElementVisible(logoutSuccessMsg, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}
	
	@Step("navigating to register page after clicking on register link....")
	public RegisterPage  goToRegisterPage() {
		eleUtil.doClick(registerlnk);
		return new RegisterPage(driver);
	}
}
