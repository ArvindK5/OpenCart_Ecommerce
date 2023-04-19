package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ConstantsUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 500: Design login page for democart application")
@Story("User story 100: develope a feature with all login page scenarios")
public class LoginPageTest extends BaseTest {
    @Description("login page title test")
    @Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("The tile of loginPage is " + title);
		Assert.assertEquals(title, ConstantsUtil.LOGIN_PAGE_TITLE);
	}
    @Description("login page url test")
    @Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		System.out.println("The tile of loginPage is " + url);
		Assert.assertTrue(url.contains(ConstantsUtil.LOGIN_URL_VALUE));
	}
	
    @Description("login page forgot pwd link test ")
    @Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotpasswordlinkTest() {
		Assert.assertTrue(loginPage.isForgotpwdlinkExist());
	}
    @Description("login Test")
    @Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		

	}
    
    @DataProvider
    public Object[][] loginNegativeData(){
    	return new Object[][]  {
    		                      {"test@gmail.com", "test@123"},
    		{" ", "test@123"},{" "," "}
    		};
    }
    
    
    @Test(priority=0,dataProvider = "loginNegativeData")
    public void loginNegativeTest(String un , String pwd) {
    	loginPage.doLoginWithWrongData(un, pwd);
    
    }
	
	
	

	

}
