package com.qa.linkedin.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class SearchResultsPage extends BasePageWeb {

	private Logger log = Logger.getLogger(SearchResultsPage.class);

	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'search-results__cluster-bottom-banner')]/a")
	private WebElement seeAllPeopleResults;

	@FindBy(xpath = "//div[contains(@class,'search-results-page')]/div")
	private WebElement searchResultsText;

	@FindBy(xpath = "//ul[@class='global-nav__primary-items']/li/a")
	private WebElement homeTab;

	String searchResultsPageTitle = "Search | LinkedIn";

	public void validateSearchResultsPageTitle() {
		log.debug("wait for the search results page title");
		wait.until(ExpectedConditions.titleContains(searchResultsPageTitle));
		Assert.assertTrue(driver.getPageSource().contains(searchResultsPageTitle));
	}

	public long getResultsCount() {
		log.debug("performing fetching results count for the given people");
		try {
			if (isPresentElement(seeAllPeopleResults)) {
				click(seeAllPeopleResults);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		log.debug("wait for the search results text");
		wait.until(ExpectedConditions.visibilityOf(searchResultsText));
		log.debug("Get the search results text from webpage");
		String txt = searchResultsText.getText();
		System.out.println("Search results Text is:" + txt);
		// txt="About 388,000 results";
		String[] str = txt.split(" ");
		// str[]=["About","388,000","results"]
		// 0 1 2
		log.debug("Results count in string format--->" + str[1]);
		String finalStringCnt = str[1].replace(",", "").trim();
		log.debug("Convert String into long integer");
		long count = Long.parseLong(finalStringCnt);
		return count;

	}
	
	public void clickHomeTab() throws Exception {
		log.debug("click on home tab");
		clickUsingJsExecutor(homeTab);
	}

}
