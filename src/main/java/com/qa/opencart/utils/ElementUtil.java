package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public void getBy(String locatorType,String value) {
		By locator=null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator=By.id(value);
			break;
		case "name":
			locator=By.name(value);
			break;
		case "classname":
			locator=By.className(value);
			break;
		case"xpath":
			locator=By.xpath(value);
			break;
		case "linktext":
			locator=By.linkText(value);
			break;
		case "tagname":
			locator=By.tagName(value);
			break;
			
		default:
			break;
		}
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public void doSendKeys(By locator ,String value) {
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public  boolean elementIsDisplayed(By locator) {
		if( getElements(locator).size()>0) {
			if(getElement(locator).isDisplayed()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEnabled(By locator) {
		return getElement(locator).isEnabled();
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public String doGetAttribute(By locator,String value) {
		return getElement(locator).getAttribute(value);
	}
	
//	********************LINKS AND IMAGES****************************
	
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public  int getElementCount(By locator) {
		return getElements(locator).size();
	}

	public  List<String> getElementsAttribute(By locator, String attribute) {
		List<WebElement> list = getElements(locator);
		List<String> attrList = new ArrayList<String>();
		for (WebElement e : list) {
			String attributeValue = e.getAttribute(attribute);
			attrList.add(attributeValue);
			// System.out.println(attrList);
		}
		return attrList;
	}

	public List<String> getElementsText(By locator) {
		List<WebElement> list = getElements(locator);
		List<String> textList = new ArrayList<String>();
		for (WebElement e : list) {
			String etext = e.getText();
			textList.add(etext);
			// System.out.println(attrList);
		}
		return textList;

	}
	
//	*************************** GOOGLE SEARCH HANDLE*************************
	public  List<String> googleSuggestionList(By locator,String searchKey) throws InterruptedException {
		List<WebElement> suggList=driver.findElements(locator);
		List<String> suggValList=new ArrayList<String>();
		for(WebElement e:suggList) {
			String text=e.getText();
			suggValList.add(text);
			System.out.println(text);
		}
		return suggValList;
	}
		public void selectGoogleSuggestionList(By locator,String suggvalue) throws InterruptedException {
			List<WebElement> suggList=driver.findElements(locator);
			for(WebElement e:suggList) {
				String text=e.getText();
				System.out.println(text);
				if(text.contains(suggvalue)) {
					e.click();
					break;
				}
			
		}}
//*************************SELECT DROPDOWN VALUES************************
			public  void selectDropDownByIndex(By locator,int number) {
				Select select=new Select(getElement(locator));
				select.selectByIndex(number);
				
			}
			public void selectDropDownByValue(By locator,String value) {
				Select select=new Select(getElement(locator));
				select.selectByValue(value);
				
			}
			
			public  void selectDropDownByVisibleText(By locator,String textvalue) {
				Select select=new Select(getElement(locator));
				select.selectByVisibleText(textvalue);
				
			}
			
			public void selectDropDownOptions(By locator,String value) {
				Select select = new Select(getElement(locator));
				List<WebElement> optionslist=select.getOptions();
				for(WebElement e:optionslist) {
					String text=e.getText();
					System.out.println(text);
					if(text.contains(value)) {
						e.click();
					}
				}
			}
			
			public  void DropDownWithoutSelectClass(By locator,String value) {
				List<WebElement> countrylist = driver.findElements(locator);
						
						for(WebElement e:countrylist) {
							String text=e.getText();
							System.out.println(text);
							if(text.contains(value)) {
								e.click();
								break;
							}
						}
					}
			
//			*****************DROPDOWN WITHOUT SELECT TAG*************
			
			public  void selectChoice(By locator,String... value) {
				List<WebElement>list = driver.findElements(locator);
				System.out.println(list.size());
				boolean flag=false;
				if(value[0].equalsIgnoreCase("all")) {
					for(WebElement e:list) {
						flag=true;
						e.click();
					}
				}
				else{
					for(WebElement e:list) {
				
					String text=e.getText();
					System.out.println(text);
					
					for(int i=0;i<value.length;i++) {
						if(text.contains(value[i])) {
							flag=true;
							e.click();
							break;
						}
					}
					
				}
			}
				if(flag==false) {
					System.out.println("choice is not availble "+value[0]);
				}
			}

//			************************Actions Class***************
			public void actionsDragDrop(By sourcelocator,By targetlocator) {
				Actions act=new Actions(driver);
				act.clickAndHold(getElement(sourcelocator)).moveToElement(getElement(targetlocator)).release().build().perform();
				
			}
			
//			****************MULTILEVEL MENU HANDLING***********
			public  void levelTwoMenuHandling(By parentMenu,By childMenu) throws InterruptedException {
				Actions act=new Actions(driver);
				act.moveToElement(getElement(parentMenu)).perform();
				Thread.sleep(3000);
				getElement(childMenu).click();
			}
			
			public  void multiLevelMenuHandling1(By parentMenu, By locator1, By locator2) throws InterruptedException {

				Actions act = new Actions(driver);
				act.moveToElement(getElement(parentMenu)).perform();
				Thread.sleep(1500);
				act.moveToElement(getElement(locator1)).perform();;
				Thread.sleep(1500);
			    getElement(locator2).click();
				Thread.sleep(1500);

			}
			
			public  void multiLevelMenuHandling(By parentMenu, String l1, String l2, String l3, String l4) throws InterruptedException {

				Actions act = new Actions(driver);
				act.moveToElement(getElement(parentMenu)).perform();
				Thread.sleep(1500);

				act.moveToElement(driver.findElement(By.linkText(l1))).perform();
				Thread.sleep(1500);

				act.moveToElement(driver.findElement(By.linkText(l2))).perform();
				Thread.sleep(1500);

				act.moveToElement(driver.findElement(By.linkText(l3))).perform();
				Thread.sleep(1500);

				driver.findElement(By.linkText(l4)).click();
			}
			
//			**********************CONTEXT CLICK*****************************
			public  void selectRightClickMenuItems(By rightClickLocator,By click_Options,String value) throws InterruptedException {
				Actions act = new Actions(driver);
				act.contextClick(getElement(rightClickLocator)).perform();
				Thread.sleep(1500);
				List<WebElement> options=driver.findElements(click_Options);
				for(WebElement e:options) {
					String text=e.getText();
					if(text.contains(value)) {
						e.click();
						break;
					}
				}
			}
			public  List<String> rightClickMenuItemsList(By rightClickLocator,By click_Options) throws InterruptedException {
				Actions act = new Actions(driver);
				act.contextClick(getElement(rightClickLocator)).perform();
				Thread.sleep(1500);
				List<WebElement> options=driver.findElements(click_Options);
				List<String> menuItems_List=new ArrayList<String>();
				for(WebElement e:options) {
					String text=e.getText();
					menuItems_List.add(text);
				}
				return menuItems_List;
			}
			
			public  void directSelectRightClickMenuItems(By rightClickLocator,By click_Option_value) throws InterruptedException {
				Actions act = new Actions(driver);
				act.contextClick(getElement(rightClickLocator)).perform();
				Thread.sleep(1500);
				getElement(click_Option_value).click();

		}
//			***********************ACTIONS CLASS SENDKEYS AND CLICK*****************
			
			public  void actionSendKeys(By locator,String value){
				Actions act=new Actions(driver);
				act.sendKeys(getElement(locator), value).perform();
			}
			public  void actionClick(By locator){
				Actions act=new Actions(driver);
				act.click(getElement(locator)).perform();
			}
			// **********************************Wait Utils *************************//

			/**
			 * An expectation for checking that the title contains a case-sensitive
			 * substring
			 * 
			 * @param titleFraction
			 * @param timeOut
			 * @return
			 */
			public String waitForTitleContains(String titleFraction, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
					return driver.getTitle();
				}
				return null;
			}

			/**
			 * An expectation for checking the title of a page.
			 * 
			 * @param title
			 * @param timeOut
			 * @return
			 */
			public String waitForTitleIs(String title, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if (wait.until(ExpectedConditions.titleIs(title))) {
					return driver.getTitle();
				}
				return null;
			}

			public String waitForUrlContains(String urlFraction, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
					return driver.getCurrentUrl();
				}
				return null;
			}

			public String waitForUrlIs(String url, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if (wait.until(ExpectedConditions.urlToBe(url))) {
					return driver.getCurrentUrl();
				}
				return null;
			}

			public void acceptAlert(int timeOut) {
				waitForAlert(timeOut).accept();
			}

			public void dismissAlert(int timeOut) {
				waitForAlert(timeOut).dismiss();
			}

			public void alertSendKeys(int timeOut, String value) {
				waitForAlert(timeOut).sendKeys(value);
			}

			public String doGetAlertText(int timeOut) {
				return waitForAlert(timeOut).getText();
			}

			public Alert waitForAlert(int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				return wait.until(ExpectedConditions.alertIsPresent()); // wait for alert and then switch to alert

			}

			public void waitForFrameByLocator(By frameLocator, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
			}

			public void waitForFrameByIndex(int frameIndex, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			}

			public void waitForFrameByElement(WebElement frameElement, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
			}

			/**
			 * An expectation for checking that an element is present on the DOM of a page.
			 * This does not necessarily mean that the element is visible.
			 * 
			 * @param locator
			 * @param timeOut
			 * @return
			 */
			public WebElement waitForElementPresence(By locator, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			}

			/**
			 * An expectation for checking that an element is present on the DOM of a page
			 * and visible. Visibility means that the element is not only displayed but also
			 * has a height and width that is greater than 0.
			 * 
			 * @param locator
			 * @param timeOut
			 * @return
			 */
			public WebElement waitForElementVisible(By locator, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}

			/**
			 * An expectation for checking an element is visible and enabled such that you
			 * can click it.
			 * 
			 * @param locator
			 * @param timeOut
			 */
			public void clickElementWhenReady(By locator, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			}

			public void waitForElementToBeClickableWithPolling(By locator, int timeOut, int pollingTime) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
				wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

			}

			/**
			 * An expectation for checking that all elements present on the web page that
			 * match the locator are visible. Visibility means that the elements are not
			 * only displayed but also have a height and width that is greater than 0.
			 * 
			 * @param locator
			 * @param timeOut
			 * @return
			 */
			public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			}

			/**
			 * An expectation for checking that there is at least one element present on a
			 * web page. Default Polling Time = 500ms
			 * 
			 * @param locator
			 * @param timeOut
			 * @return
			 */
			public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			}

			/**
			 * An expectation for checking that there is at least one element present on a
			 * web page. Default Polling Time = customized
			 * 
			 * @param locator
			 * @param timeOut
			 * @return
			 */
			public List<WebElement> waitForElementsPresence(By locator, int timeOut, int pollingTime) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
				return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			}

			public void waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime, String message) {

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
						.pollingEvery(Duration.ofMillis(pollingTime))
						.ignoring(NoSuchElementException.class, StaleElementReferenceException.class).withMessage(message);

				wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
			}

			///
			public WebElement retryingElement(By locator, int timeOut, int pollingTime) {

				WebElement element = null;

				int attempts = 0;
				while (attempts < timeOut) {
					try {
						element = driver.findElement(locator);
						break;
					} catch (NoSuchElementException e) {
						System.out.println("element is not found in attempt : " + attempts + " for : " + locator);
						try {
							Thread.sleep(pollingTime);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					attempts++;
				}

				if (element == null) {
					try {
						throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
					} catch (Exception e) {
						System.out.println("element is not found exception....tried for : " + timeOut + " secs"
								+ " with the interval of : " + pollingTime + " ms ");
					}
				}

				return element;

			}

			public void waitForPageLoad(int timeOut) {

				long endTime = System.currentTimeMillis() + timeOut;

				while (System.currentTimeMillis() < endTime) {

					JavascriptExecutor js = (JavascriptExecutor) driver;
					String state = js.executeScript("return document.readyState").toString();
					System.out.println("page is : " + state);
					if (state.equals("complete")) {
						System.out.println("page is fully loaded now....");
						break;
					}

				}

			}

		}
