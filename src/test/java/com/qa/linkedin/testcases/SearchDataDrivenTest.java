package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.pages.SignoutOverlayPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase{
 
	private Logger log=Logger.getLogger(SearchDataDrivenTest.class);
	private String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchdata.xlsx";
	LinkedinHomePage lHmPage;
	LinkedinLoggedinPage llPage;
	SearchResultsPage srPage;
	SignoutOverlayPage overlayPg;
	
	
  @BeforeClass
  public void beforeClass() throws Exception {
	  log.debug("Pages initilization...");
	  lHmPage=new LinkedinHomePage();
	  llPage=new LinkedinLoggedinPage();
	  srPage=new SearchResultsPage();
	  overlayPg=new SignoutOverlayPage();
	  log.debug("calling title verification");
	  lHmPage.verifyLinkedinHomePageTitle();
	  lHmPage.verifyLinkedinLogo();
	  lHmPage.clickSigninLink();
	  lHmPage.verifySignInHeaderText();
	  llPage=lHmPage.doLogin(readPropertyValue("username"), readPropertyValue("password"));
	  llPage.verifyProfileRailCard();
  }

  @AfterClass
  public void afterClass() throws Exception {
	log.debug("perform the logout operation from application");
	llPage.doLogout();
	
	overlayPg.clickFinalSignOutBtn();
	lHmPage.verifyLinkedinHomePageTitle();
  }

  
  @Test(dataProvider="getData")
  public void searchDataDrivenTest(String keyword) throws Exception {
	  log.debug("Started executing the search test for people:"+keyword);
	  srPage=llPage.doPeopleSearch(keyword);
	  srPage.validateSearchResultsPageTitle();
	 long cnt=srPage.getResultsCount();
	 log.debug("search results count for "+keyword+" is:"+cnt);
	 log.debug("click on Hometab to go to loggedin page");
	 srPage.clickHomeTab();
	 log.debug("***********************Iteration done***************************"); 
  }
  
  
  @DataProvider
  public Object[][] getData() throws InvalidFormatException, IOException{
	 Object[][] data=new ExcelUtils().getTestData(path, "Sheet1"); 
	 return data;
  }
  
}
