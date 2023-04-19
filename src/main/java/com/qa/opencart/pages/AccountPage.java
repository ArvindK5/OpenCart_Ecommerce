package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ConstantsUtil;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	private ElementUtil elementUtil;
	private WebDriver driver;

	private By accSections = By.cssSelector("div#content h2");
	//private By header = By.cssSelector("div#logo");
	private By logoutlink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);	
	}
	public String getaccPageTitle() {
		 return elementUtil.waitForTitle(5, ConstantsUtil.ACCOUNTS_PAGE_TITLE);
	}
	public String getAccountPageurl() {
		return elementUtil.getPageUrl();
		
	}
	
//	public String getAccPageHeader() {
//		return elementUtil.doGetText(header);
//	}
	
	public List<String>  getAccountSectionList() {
		List<String> accsecValList = new ArrayList<String>();
		List<WebElement> accSecList =  elementUtil.waitForVisibiltyOfAllElement(accSections, 5);
		for(WebElement  e: accSecList) {
			accsecValList.add(e.getText());
			
		}
		Collections.sort(accsecValList);
		return accsecValList;
	}
	
	public Boolean isLogoutexist() {
		return elementUtil.doIsDisplay(logoutlink);
	}
	//search method:
	public SearchResultPage doSearch(String productname) {
		System.out.println("searching the product " + productname);
		elementUtil.doclear(searchField);
		elementUtil.doSendKeys(searchField, productname);
		elementUtil.doClick(searchButton);
		 return new SearchResultPage(driver);
		
	}

}
