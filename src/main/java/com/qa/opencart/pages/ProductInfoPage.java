package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	ElementUtil elementUtil ;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails li img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By successmessage = By.cssSelector("div.alert.alert-success.alert-dismissible");
	

	
	
			
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil =new ElementUtil(driver);
		
	}
	
	public String getProductHeadterText() {
		return elementUtil.doGetText(productHeader);
	}
	
	public int getImagesCount() {
		return elementUtil.getElements(productImages).size();
	}
	
	/**
	 * This method will collect the meta data and pricing data information in the form of 
	 * HashMap.
	 * This method will return productInfoMap.
	 * @return 
	 */
	public Map<String, String> getProductInformation()  {
		Map<String,String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeadterText());
		List<WebElement> metaDataList = elementUtil.getElements(productMetaData);
		System.out.println("total product meta data:" + metaDataList.size());
		for(WebElement e :metaDataList) {
			//Brand: Apple
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		
		//:Price
		List<WebElement> priceList = elementUtil.getElements(productPriceData);
		String price = priceList.get(0).getText().trim();
		String Exprice = priceList.get(1).getText().trim();
		
		productInfoMap.put("price", price);
		productInfoMap.put("ExTaxPrice", Exprice);
		return productInfoMap;
	
	}
	
	public void selectQuantity(String qty) {
		elementUtil.doClick(addToCart);
	}
	public String getSucessMessage(String qty) {
		return elementUtil.doGetText(successmessage);
	}


}
