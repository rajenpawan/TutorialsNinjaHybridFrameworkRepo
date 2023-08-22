package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	//public void loadPropertiesFile() {
		public Base() {
		//Properties prop = new Properties();
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+("\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties"));
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try {
		FileInputStream dataFis = new FileInputStream(dataPropFile);
		dataProp.load(dataFis);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) throws InterruptedException {

		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		
		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//		Thread.sleep(1000);
		driver.get(prop.getProperty("url")); //config.properties
//		Thread.sleep(3000);
		
		return driver;
	}
	
}
