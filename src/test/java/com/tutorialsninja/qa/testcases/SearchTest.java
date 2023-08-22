package com.tutorialsninja.qa.testcases;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{

	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		HomePage homePage = new HomePage(driver);
//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct")); //validProduct from testdata.properties
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		homePage.clickonSearchButton();
//		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		SearchPage searchPage = new SearchPage(driver);

		Assert.assertTrue("Valid Product HP is not displayed in the search results", searchPage.displayStatusOfHpValidProduct());
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		HomePage homePage = new HomePage(driver);
//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		homePage.clickonSearchButton();
		SearchPage searchPage = new SearchPage(driver);
//		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResult"), "No Products Message in Search results is not displayed");
		
	}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct() throws InterruptedException {
		//driver.findElement(By.name("search")).sendKeys("");
		HomePage homePage = new HomePage(driver);
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		homePage.clickonSearchButton();
//		Thread.sleep(2000);
//		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		SearchPage searchPage = new SearchPage(driver);
//		String ExpectedSearchMessage = dataProp.getProperty("noProductTextInSearchResult");
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(dataProp.getProperty("noProductTextInSearchResult"), actualSearchMessage);
	}
	
	
}

