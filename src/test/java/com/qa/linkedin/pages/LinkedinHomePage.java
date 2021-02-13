package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb{
	
	private Logger log = Logger.getLogger(LinkedinHomePage.class);
	
	//create a constructor
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css="a.nav__logo-link")
	private WebElement linkedinLogo;
	
	@FindBy(linkText="Sign in")
	private WebElement signInLink;
	
	@FindBy(xpath="//h1[contains(@class,'header__content__heading')]")
	private WebElement signInHeaderText;
	
	@FindBy(id="username")
	private WebElement emailEditbox;
	
	@FindBy(name="session_password")
	private WebElement passwordEditbox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	private WebElement signInButton;
	
	@FindBy(xpath = "//button[@class='secondary-action'][contains(.,'Skip')]")
	private WebElement skipBtn;
	
	String signinPageTitle = "LinkedIn Login, Sign in | LinkedIn";
	String homePageTitle="LinkedIn: Log In or Sign Up";
	
	public void verifyLinkedinHomePageTitle() {
		log.debug("wait for the linkedin home page title");
		wait.until(ExpectedConditions.titleContains(homePageTitle));
		Assert.assertEquals(driver.getTitle(), homePageTitle);
	}
	
	public void verifyLinkedinLogo() {
		log.debug("wait for the linkedin logo");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed(),"Linkedin logo is not present");
	}
	
	public void clickSigninLink() throws Exception {
		log.debug("click on sign in link in homepage");
		click(signInLink);
	}
	
	public void verifyLinkedinSigninPageTitle() {
		log.debug("wait for the linkedin signin page title");
		wait.until(ExpectedConditions.titleContains(signinPageTitle));
		Assert.assertEquals(driver.getTitle(), signinPageTitle);
	}
	
	public void verifySignInHeaderText() {
		log.debug("wait for the sign in header text");
		wait.until(ExpectedConditions.visibilityOf(signInHeaderText));
		Assert.assertTrue(signInHeaderText.isDisplayed(),"signInHeaderText is not present");
	}
	
	public void clickSigninButton() throws Exception {
		log.debug("click on sign in Button in Signin page");
		click(signInButton);
	}
	
	public void clickSkipBtn() throws Exception {
		log.debug("click on skip button  in sign in process page");
		click(skipBtn);
	}
	
	public LinkedinLoggedinPage doLogin(String uname,String pwd) throws Exception {
		log.debug("started login process.....");
		sendKey(emailEditbox,uname);
		sendKey(passwordEditbox,pwd);
		clickSigninButton();
		try {
			if(isPresentElement(skipBtn)) {
				click(skipBtn);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new LinkedinLoggedinPage();
	}
	
}
