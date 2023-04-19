package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;
	ElementUtil elementUtil;
	
	private By SearchItemResult = By.cssSelector("div.product-thumb");
	private By resultsItems = By.cssSelector("div.product-thumb h4 a");
	
	public SearchResultPage(WebDriver driver ) {
		this.driver = driver;
		elementUtil = new  ElementUtil(driver);
	}
	
	public int getProductResultsCount() {
		return elementUtil.getElements(SearchItemResult).size();
	}
	
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemList = elementUtil.getElements(resultsItems);
		System.out.println("total number of items displayed for :" + productName + " " + resultItemList.size());
		
		for (WebElement e : resultItemList) {
			if(e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
		
		
	}
	

}
