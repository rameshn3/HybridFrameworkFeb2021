package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb{
private Logger log = Logger.getLogger(LinkedinLoggedinPage.class);
	
	//create a constructor
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css="div[class*='profile-rail-card']")
	private WebElement profileRailCard;
	
	@FindBy(xpath="//img[@class='global-nav__me-photo ember-view']")
	private WebElement profileImageIcon;
	
	@FindBy(xpath = "//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement signOutLink;

	
	@FindBy(xpath = "//input[contains(@class,'search-global-typeahead')]")
	private WebElement searchEditbox;
	
	
	
	public void verifyProfileRailCard() {
		log.debug("wait for the Profile rail card");
		wait.until(ExpectedConditions.visibilityOf(profileRailCard));
		Assert.assertTrue(profileRailCard.isDisplayed(), "Profile rail card is not available");
	}
	
	public void doLogout() throws Exception {
		log.debug("Perform the logout operation from the application");
		click(profileImageIcon);
		click(signOutLink);
	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws Exception {
		log.debug("Perform the people search");
		sendKey(searchEditbox,keyword);
		Thread.sleep(2000);
		log.debug("Press the enter key to submit the search form");
		searchEditbox.sendKeys(Keys.ENTER);
		return new SearchResultsPage();
	}
}
