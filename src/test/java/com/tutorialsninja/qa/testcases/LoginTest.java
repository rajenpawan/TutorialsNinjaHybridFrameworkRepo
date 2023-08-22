package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base{
	
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		
//		String browserName = "firefox";
//		
//		if(browserName.equals("chrome")) {
//			driver = new ChromeDriver();
//		}else if(browserName.equals("firefox")) {
//			driver = new FirefoxDriver();
//		}else if(browserName.equals("edge")) {
//			driver = new EdgeDriver();
//		}
//		
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
//		driver.get("http://tutorialsninja.com/demo/");
//		Thread.sleep(3000);
		
		//loadPropertiesFile();
		//Thread.sleep(1000);
	    driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		Thread.sleep(1000);
		HomePage homePage = new HomePage(driver);
//		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		homePage.clickOnMyAccount();
//		driver.findElement(By.linkText("Login")).click();
		homePage.selectLoginOption();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
//	@Test (priority=1,dataProvider="supplyTestData")
	@Test(priority=1,dataProvider="validCredentialsProvider")
	public void LoginwithValidCredentials(String email,String password) throws InterruptedException {
	
	LoginPage loginPage = new LoginPage(driver);
//	driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//	driver.findElement(By.id("input-email")).sendKeys(email);
	loginPage.enterEmailAddres(email); //email in ExcelSheet "TutorialsNinjaTestData.xls"
//	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//	driver.findElement(By.id("input-password")).sendKeys(password);
	loginPage.enterPassword(password); //password in ExcelSheet "TutorialsNinjaTestData.xls"
//	driver.findElement(By.id("input-email")).sendKeys(testa);
//	driver.findElement(By.xpath("//input[@value='Login']")).click();
	loginPage.clickOnLoginButton();
	
//	Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	AccountPage accountPage = new AccountPage(driver);
	Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());
	}
	
	@DataProvider(name="validCredentialsProvider")
	public Object[][] supplyTestData() {
		
//		Object[][] data = {{"amotooricap3@gmail.com","12345"},{"amotooricap7@gmail.com","12345"},{"amotooricap1@gmail.com","12345"}};
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
		
	}
	
	@Test (priority=2)
	public void verifyLoginwithInvalidCredentials() {
		
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddres(Utilities.generateEmailwithTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));//validPassword in config.properties
		loginPage.enterPassword(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginButton();
//		System.out.println("System File Path: " + System.getProperty("user.dir"));
//		String actualWarningMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarningMsg = loginPage.RetrieveEmailPasswordNotMatchingWarningMessage();
		String ExpectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(ExpectedWarningMsg), "Expected warning message not displayed");
	
	}
	
	@Test (priority=4)
	public void verifyLoginwithValidEmailAndInvalidPassword() {
		LoginPage loginPage = new LoginPage(driver);
		
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));//validEmail in config.properties
		loginPage.enterEmailAddres(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginButton();
		
//		String actualWarningMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarningMsg = loginPage.RetrieveEmailPasswordNotMatchingWarningMessage();
		String ExpectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(ExpectedWarningMsg), "Expected warning message not displayed");
	
	}
	
	@Test (priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		LoginPage loginPage = new LoginPage(driver);
		
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
		loginPage.enterEmailAddres(Utilities.generateEmailwithTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginButton();
		
//		String actualWarningMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarningMsg = loginPage.RetrieveEmailPasswordNotMatchingWarningMessage();
		String ExpectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(ExpectedWarningMsg), "Expected warning message not displayed");
	
	}
	
	@Test (priority=5)
	public void verifyLoginWithoutProvidingCredentials() {
		LoginPage loginPage = new LoginPage(driver);
		//driver.findElement(By.id("input-email")).sendKeys("");
		//driver.findElement(By.id("input-password")).sendKeys("");
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginButton();
		
//		String actualWarningMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarningMsg = loginPage.RetrieveEmailPasswordNotMatchingWarningMessage();
		String ExpectedWarningMsg = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(ExpectedWarningMsg), "Expected warning message not displayed");
	
	}
	

}
