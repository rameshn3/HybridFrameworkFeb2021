package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignoutOverlayPage extends BasePageWeb{
private Logger log = Logger.getLogger(SignoutOverlayPage.class);
	
	//create a constructor
	public SignoutOverlayPage() {
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//span[@class='artdeco-button__text' and contains(text(),'Sign out')]")
	private WebElement lastSignOutBtn;
	
	public void clickFinalSignOutBtn() throws InterruptedException {
		
		log.debug("perfrom the final sign out button on the overlay popup");
		clickUsingJsExecutor(lastSignOutBtn);
	}
}
