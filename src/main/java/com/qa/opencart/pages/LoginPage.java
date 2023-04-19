package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ConstantsUtil;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver ;
	private ElementUtil elementUtil;
	
	//By Locators
	private By username  = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By forgotpasswordlink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By registerLink = By.linkText("Register");
	private By loginErrorMesg = By.cssSelector("div.alert");
	//2.Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
		
	}
	//public Page actions(methods)
	@Step("getting login page title")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitle(5, ConstantsUtil.LOGIN_PAGE_TITLE);
	}
	
	@Step("getting login page url")
	public String getLoginPageUrl() {
		return elementUtil.getPageUrl();
		
	}
	@Step("forgot password")
	public boolean isForgotpwdlinkExist() {
		return elementUtil.doIsDisplay(forgotpasswordlink);
		
	}
	
	@Step("loin with username : {0} and password : {1}")
	public AccountPage doLogin(String un , String pwd) {
	elementUtil.doSendKeys(username, un);
	elementUtil.doSendKeys(password, pwd);
	elementUtil.doClick(loginButton);
	
	return new AccountPage(driver);

	
	}
	
	@Step("loin with username : {0} and password : {1}")
	public boolean doLoginWithWrongData(String un , String pwd) {
	elementUtil.doSendKeys(username, un);
	elementUtil.doSendKeys(password, pwd);
	elementUtil.doClick(loginButton);
	return elementUtil.doIsDisplay(loginErrorMesg);
	

	
	}
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}

}
