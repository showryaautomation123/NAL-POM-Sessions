package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	private	By productNameLink;
	private By searchResults = By.cssSelector("div.product-layout.product-grid");
			
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}
	
	public int getSearchResultsCount() {
		return eleutil.waitForElementsVisible(searchResults, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}

	public ProductInfoPage selectProduct(String productName) {
		productNameLink =  By.linkText(productName); 
		eleutil.doClick(productNameLink);
		return new ProductInfoPage(driver);
	}
}
