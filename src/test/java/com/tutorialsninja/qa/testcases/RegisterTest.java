package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		Thread.sleep(1000);
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		Thread.sleep(1000);
		HomePage homePage = new HomePage(driver);
//		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		homePage.clickOnMyAccount();
//		driver.findElement(By.xpath("//*[contains(text(),\"Register\")]")).click();
		homePage.selectRegisterOption();
		
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
		
	}

	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() throws InterruptedException {
		RegisterPage registerPage = new RegisterPage(driver);
//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp())
		registerPage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@name='agree']")).click();
		registerPage.selectPrivacyPolicy();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		registerPage.clickOnContinueButton();
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page not displayed!");
		Thread.sleep(2500);
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountWithExistingEmailAddress() throws InterruptedException {
		
		//Thread.sleep(3000);
		RegisterPage registerPage = new RegisterPage(driver);
//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@name='agree']")).click();
		registerPage.selectPrivacyPolicy();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		registerPage.clickOnContinueButton();
		
//		String actualWarning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateWarningEmail")), "Warning message not founds");
		
		Thread.sleep(2500);

	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		
		//Thread.sleep(3000);
		
		//driver.findElement(By.xpath("//input[@name='agree']")).click();
		RegisterPage registerPage = new RegisterPage(driver);
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		registerPage.clickOnContinueButton();
		
//		String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualPrivacyPolicyWarningMessage = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy Policy message is not displayed!");
		
//		String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning,dataProp.getProperty("firstNameWarning"),"First Name warning message not displayed"); //firstNameWarning from testdata.prop
		
//		String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		String actualLastNameWarning = registerPage.retrievelastNameWarning();
		Assert.assertEquals(actualLastNameWarning,dataProp.getProperty("lastNameWarning"),"Last Name warning message not displayed");
		
//		String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		String actualEmailWarning = registerPage.retrieveemailWarning();
		Assert.assertEquals(actualEmailWarning,dataProp.getProperty("emailWarning"),"Email warning message not displayed");
		
//		String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		String actualTelephoneWarning = registerPage.retrievetelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning,dataProp.getProperty("telephoneWarning"),"Telephone warning message not displayed");
		
//		String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		String actualPasswordWarning = registerPage.retrievepasswordWarning();
		Assert.assertEquals(actualPasswordWarning,dataProp.getProperty("passwordWarning"),"Password warning message not displayed");
		
		}
	
}
