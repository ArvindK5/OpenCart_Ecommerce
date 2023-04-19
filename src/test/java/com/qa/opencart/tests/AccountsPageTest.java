package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.utils.ConstantsUtil;

public class AccountsPageTest  extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String title = accPage.getaccPageTitle();
		System.out.println("acc page title is : " + title);
		Assert.assertEquals(title,  ConstantsUtil.ACCOUNTS_PAGE_TITLE);
		
		
	}
	@Test
	public void accSectionsListTest() {
		 List<String> secList =  accPage.getAccountSectionList();
		 secList.stream().forEach(e -> System.out.println(e));
		 System.out.println("Before Sort " + ConstantsUtil.EXP_ACC_SEC_LIST);
		 Collections.sort(ConstantsUtil.EXP_ACC_SEC_LIST);
		 System.out.println("After Sort " + ConstantsUtil.EXP_ACC_SEC_LIST);

		 Assert.assertEquals(secList, ConstantsUtil.EXP_ACC_SEC_LIST);
	}
	
	@Test
	public  void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutexist());
	}

}
