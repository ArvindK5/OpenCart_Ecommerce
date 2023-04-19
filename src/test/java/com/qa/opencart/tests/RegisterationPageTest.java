package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ConstantsUtil;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterationPageTest extends BaseTest {
	@BeforeClass
	public void setupRegister() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(ConstantsUtil.REGISTER_SHEET_NAME);
		return regData;
		
	}
	
	//random
	public String getRandomNumber() {
		Random randomGenerator = new Random();
		String email = "testautomation" + randomGenerator.nextInt(1000) + "@gmail.com";
		return email;
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistraionTest(String firstName, String lastName, String telephone,
			String password, String subscribe) {
		
		
		Assert.assertTrue(registrationPage.accountRegisteration( firstName,  lastName, 
				 getRandomNumber(),  telephone,
				 password,  subscribe));
	}
}
