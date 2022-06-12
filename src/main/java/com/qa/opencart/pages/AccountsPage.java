package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	private By header = By.cssSelector("div#logo a");
	private By search = By.name("search");
	private By logoutLnk = By.linkText("Logout");
	private By sectionHeaders = By.cssSelector("div#content h2");
	private By searchIcon = By.cssSelector("div#search button");
	private By headerName=By.id("header");
	


	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}

	public String getAccountsPageTitle() {
		return eleutil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}

	public String getAccountsPageUrl() {
		return eleutil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}

	public String getAccountsPageHeader() {
		return eleutil.doGetText(header);
	}
	
	public List<String> getAccountsPageSectionList() {
		List<WebElement> sectionHeaderlist = eleutil.getElements(sectionHeaders);
		List<String>sectionvalueList=new ArrayList<String>();
		for(WebElement e:sectionHeaderlist) {
			String text=e.getText();
			sectionvalueList.add(text);
		}
		return sectionvalueList;
	}
	
	public boolean isLogoutLinkExist() {
		return eleutil.waitForElementVisible(logoutLnk, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	
	public boolean isSearchExist() {
		return eleutil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	
	public LoginPage clickOnLogoutLnk() {
		if(isLogoutLinkExist()) {
			eleutil.doClick(logoutLnk);
		}
		return new LoginPage(driver);
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		if(isSearchExist()) {
			eleutil.doSendKeys(search, searchKey);
			eleutil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		return null;
	}
}
