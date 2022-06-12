package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Epic - 200: this epic is for Accounts page of open cart application")
@Story("ACCPAGE - 201: design Accounts page with various features")

public class AccountsPageTest extends BaseTest {
	
	

	@BeforeClass
	public void accSetUp() {
		accpage = loginpage.doLogIn(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		accpage=new AccountsPage(driver);
	}

	@Test(priority=0)
	public void accountsPageTitleTest() {
		String actTitle = accpage.getAccountsPageTitle();
		System.out.println("Account Page Title  :" + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority=2)
	public void accountsPageUrlTest() {
		String acturl = accpage.getAccountsPageUrl();
		System.out.println("Account page Url :" + acturl);
		Assert.assertTrue(acturl.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}

	@Test
	public void accountsPageHeaderTest() {
		Assert.assertEquals(accpage.getAccountsPageHeader(), Constants.ACCOUNTS_PAGE_HEADER);
	}

	@Test(priority=4)
	public void accountsPageSectionList() {
		List<String> actAccSectionList = accpage.getAccountsPageSectionList();
		Assert.assertEquals(actAccSectionList, Constants.EXPECTED_ACCOUNTSPAGE_SECTION_LIST);
	}

	@Test(priority=6)
	public void logoutLinkTest() {
		Assert.assertTrue(accpage.isLogoutLinkExist());
	}

	@Test(priority=8)
	public void SearchExistTest() {
		Assert.assertTrue(accpage.isSearchExist());
	}

//	@Test(priority=0)
//	public void LogoutTest() {
//		Assert.assertEquals(accpage.clickOnLogoutLnk().getLogoutSuccessMsg(), Constants.LOGOUT_SUCCESS_MESSAGE);
//	}
	
	
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	@Test(dataProvider="getSearchKey",priority=10)
	public void searchTest(String searchKey) {
		searchResPage=accpage.doSearch(searchKey);
		Assert.assertTrue(searchResPage.getSearchResultsCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, 
			{ "Macbook", "MacBook Air" }, 
			{ "Macbook", "MacBook" },
				{ "iMac", "iMac" }, 
				{ "Apple", "Apple Cinema 30\"" }, 
				{ "Samsung", "Samsung SyncMaster 941BW" } };
	}

	@Test(dataProvider = "getProductName",priority=12,enabled=false)
	public void selectProductTest(String searchKey, String productName) {
		searchResPage = accpage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String productHeader = productInfoPage.getProductHeaderName();
		System.out.println("product header: " + productHeader);
		Assert.assertEquals(productHeader, productName);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook", "MacBook Pro",4 }, 
				{ "Samsung", "Samsung SyncMaster 941BW" ,1} };
	}

	@Test(dataProvider = "getProductData",priority=14,enabled=false)
	public void productImageTest(String searchKey, String productName, int productImageCount) {
		searchResPage = accpage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), productImageCount);
	}
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] { { "Macbook", "MacBook Pro","Product 18","In Stock","800","$2,000.00" }, 
				{ "Samsung", "Samsung Galaxy Tab 10.1" ,"SAM1","Pre-Order","1000","$241.99"}, 
		{"iMac","iMac","Product 14","In Stock",null,"$122.00"}};
	}

	@Test(dataProvider = "getProductInfoData",priority=16,enabled=false)
	public void productInfoTest(String searchKey,String productName,String productCode,String availability,String rewardPoints,String price) {
		searchResPage = accpage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		Map<String, String> actProductInfoMap = productInfoPage.getProductInformation();
		softAssert.assertEquals(actProductInfoMap.get("name"), productName);
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), productCode);
		softAssert.assertEquals(actProductInfoMap.get("Availability"), availability);
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), rewardPoints);
		softAssert.assertEquals(actProductInfoMap.get("price"), price);
		softAssert.assertAll();
	}
	
	@Test(priority=18,enabled=false)
	public void productInfoDescriptionTest() {
		searchResPage = accpage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains(Constants.PRODUCTINFO_PAGE_HEADER_DESCRIPTION));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains(Constants.PRODUCTINFO_PAGE_DESCRIPTION));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains(Constants.PRODUCTINFO_PAGE_HEADER2_DESCRIPTION));
		softAssert.assertAll();
	}
	
	@Test(priority=20)
	public void addToCartTest() {
		searchResPage = accpage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		String actSuccessMessg = productInfoPage
				.enterQty("1")
							.clickOnAddToCart()
									.getCartSuccessMessg();
		System.out.println("cart messg: " + actSuccessMessg);
		softAssert.assertTrue(actSuccessMessg.contains("MacBook Pro"));
		String actCartItemText = productInfoPage.getCartItemText();
		softAssert.assertTrue(actCartItemText.contains("1" + " item(s)"));
	}
	
	@Test(priority=22)
	public void checkShoppingCartTest() {
		searchResPage = accpage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		String shoppingCartText=productInfoPage
		.enterQty("1")
					.clickOnAddToCart()
					.clickOnItemsCart()
					.clickOnViewCart()
					.getShoppingcartText();
	Assert.assertTrue(shoppingCartText.contains("Shopping Cart"));
	}
	
}