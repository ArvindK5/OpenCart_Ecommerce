package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	// Constructor for WebDriver usage
	public ElementUtil(WebDriver driver) {                                                                                                

		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);

	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
			
		}
		return element;
	}
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	public void doclear(By locator) {
		 driver.findElement(locator).clear();
	}

	public void doSendKeys(By locator, String value) {
		WebElement element =getElement(locator);
		element.clear();
		getElement(locator).sendKeys(value);
	}
	
	 public  String doGetText(By locator) {
		 return getElement(locator).getText();
	 }
	public  boolean doIsDisplay(By locator) {
		  return getElement(locator).isDisplayed();
	}

	////// WAIT UTILS
	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);// selenium4 update
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public Alert waitforAlertpresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public String getAlerttext(int timeOut) {
		return waitforAlertpresent(timeOut).getText();
	}

	public void acceptalert(int timeOut) {
		waitforAlertpresent(timeOut).accept();
	}

	public void dismiss(int timeOut) {
		waitforAlertpresent(timeOut).dismiss();
	}

	public String waitForTitle(int timeOut, String Title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut); // 200 is polling time ,default is 500

		wait.until(ExpectedConditions.titleIs(Title));
		return driver.getTitle();

	}

	public String waitForTitleIs(int timeOut, String Title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut); // 200 is polling time ,default is 500

		wait.until(ExpectedConditions.titleContains(Title));
		return driver.getTitle();

	}

	public String waitForTitle(int timeOut, String Title, int intervaletime) // Method Overloading
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOut, intervaletime); // 200 is polling time ,default is 500

		wait.until(ExpectedConditions.titleIs(Title));
		return driver.getTitle();

	}
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	public void doClick(By locator) {
		getElement(locator).click();
		
	}

	public Boolean waitForURL(int timeOut, String url) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut); // 200 is polling time ,default is 500

		return wait.until(ExpectedConditions.urlContains(url));
	}

	// frame
	public void waitForFrameandswitchtoit(String idorName, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idorName));

	}

	public void waitForFrameandswitchtoit(By Locator, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Locator));

	}

	public void waitForFrameandswitchtoit(int index, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));

	}

	public void waitForFrameandswitchtoit(WebElement frameElement, int TimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));

	}
       
	public List<WebElement> waitForVisibiltyOfAllElement(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	//fluentwait
    public  WebElement waitForElementwithFluentWait(By locator, int timeout, long pollingTime) {
   	 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingTime))  //default :5
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
	    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }
    
    public  Alert waitForAlert(By locator, int timeout, long pollingTime) {
   	 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingTime))  //default :5
                .ignoring(NoAlertPresentException.class);
   	        
                
	    return wait.until(ExpectedConditions.alertIsPresent());

    }
    
    public  WebDriver waitForFrame(String framelocator, int timeout, long pollingTime) {
   	 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingTime))  //default :5
                .ignoring(NoAlertPresentException.class);
   	        
                
	    return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));

    }
    
    //Actions class
    public  void doActionssendkeys(By locator,String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		
		
	}
     
	public  void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
		
	}
	//drop down utils
	public  void doSelectDropDownValueByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
			}
	
	public  void doSelectDropDownValueByVisibleText(By locator, String  text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
			}
	
	public  void doSelectDropDownValueByValue(By locator, String  value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
			}
	
	public void doSelectDropDownValue(By locator , String value) {

		Select select = new Select(getElement(locator));
			List<WebElement> optionsList = select.getOptions();
			System.out.println(optionsList.size());
			
			for(WebElement e : optionsList) {
				String text = e.getText();
				if(text.equals("China")) {
					e.click();
					break;
				}
			}

	}

}
