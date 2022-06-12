package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductComparisonpage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By myaccount = By.xpath("//li[@class='dropdown']/a[@title='My Account'] ");
	private By login = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Login')]");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By productNameLink;
	private By addToCartBtn = By.id("button-cart");
	private By toolTip = By.xpath("//button[@data-original-title='Compare this Product']");
	private By productComparison = By.linkText("product comparison");
	private By productPrice = By.xpath("(//div[@id='content']//tr)[4]//td");
	private By remove=By.xpath("//div[@id='content']//a[contains(text(),'Remove')]");

	public ProductComparisonpage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		//eleUtil.waitForElementVisible(myaccount, Constants.DEFAULT_ELEMENT_TIMEOUT).click();
		//eleUtil.waitForElementVisible(login, Constants.DEFAULT_ELEMENT_TIMEOUT).click();
	}

	public ArrayList<String> searchProduct(String searchKey, String productName, String searchKey1,
			String productName1) {
		WebElement searchBox = eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIMEOUT);
		searchBox.clear();
		searchBox.sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		productNameLink = By.linkText(productName);
		eleUtil.doClick(productNameLink);
		eleUtil.doClick(addToCartBtn);
		eleUtil.doClick(toolTip);
		eleUtil.waitForElementVisible(productComparison, Constants.DEFAULT_ELEMENT_TIMEOUT).click();
		WebElement searchBox1 = eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIMEOUT);
		searchBox1.clear();
		searchBox1.sendKeys(searchKey1);
		eleUtil.doClick(searchIcon);
		productNameLink = By.linkText(productName1);
		eleUtil.doClick(productNameLink);
		eleUtil.doClick(addToCartBtn);
		eleUtil.doClick(toolTip);
		eleUtil.waitForElementVisible(productComparison, Constants.DEFAULT_ELEMENT_TIMEOUT).click();
		List<WebElement> pricelist = eleUtil.waitForElementsVisible(productPrice, Constants.DEFAULT_ELEMENT_TIMEOUT);
		ArrayList<String> productsprice = new ArrayList<String>();
//		for (WebElement e : pricelist) {
//			String price = e.getText();
//			productsprice.add(price);
//			//System.out.println(productsprice);
//		}
		
		String product1=pricelist.get(1).getText();
		String product2=pricelist.get(2).getText();
		productsprice.add(product1);
		productsprice.add(product2);

		eleUtil.doClick(remove);
		eleUtil.doClick(remove);
		return productsprice;
	}
}