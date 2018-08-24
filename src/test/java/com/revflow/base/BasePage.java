package com.revflow.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.revflow.utilities.ExcelReader;
import com.revflow.utilities.ExtentManager;
import com.revflow.utilities.TestUtil;

public class BasePage {

/**@author ajay.kumar4
 */
	
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static Properties OR = new Properties();	
	public static ExtentTest test;
	public ExtentReports rep = ExtentManager.getInstance();
    public static WebDriver driver;
    public static ExcelReader excel = new ExcelReader(
	System.getProperty("user.dir") + "\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
     
    @BeforeSuite
	public void setUp() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				log.debug("Config file Not Found !!!");
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				log.debug("Config file Not loaded !!!");
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				log.debug("OR file Not Found !!!");
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				log.debug("OR file Not loaded !!!");
				e.printStackTrace();
			}
		}

	}   

 

	

public void browserLaunch(String browser,String url){
	 
	
	 
	if(browser.equalsIgnoreCase("firefox")) {
		
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\resources\\executables\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		log.debug("Firefox Launched !!!");
		
		
	}else if(browser.equalsIgnoreCase("chrome")){
		
		System.setProperty("webdriver.chrome.driver",
		System.getProperty("user.dir") + "\\resources\\executables\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		log.debug("Chrome Launched !!!");
		
	}else if(browser.equalsIgnoreCase("IE")){
		
		System.setProperty("webdriver.ie.driver",
	    System.getProperty("user.dir") + "\\resources\\executables\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		log.debug("IE Launched !!!");
		
		
	}
	 
	 driver.get(url);
	 log.debug("Navigated to : " + url);
	 driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
	 wait = new WebDriverWait(driver, 5);
	 
	 
 }
 
 
 
 
 @BeforeTest
 public void beforeTest() {
 
	  browserLaunch(config.getProperty("browser"),config.getProperty("url"));
 }
 

    @AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}

		log.debug("test execution completed !!!");
	}
 
	

    
    // *******************************Common Methods*******************************
    
    
    
	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.ERROR, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.INFO, test.addScreenCapture(TestUtil.screenshotName));
            Assert.assertFalse(true,t.getMessage());
		}

	}
	
	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}


	static WebElement dropdown;
	public void select(String locator, String value) {

	/*	if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}*/
		dropdown = driver.findElement(By.xpath(locator));
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO, "Selecting from dropdown :   value as " + value);

	}
	
	public void type(String locator, String value) {

	/*	if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}*/
		
		driver.findElement(By.xpath(locator)).sendKeys(value);

		test.log(LogStatus.INFO, "Typing in : entered value as " + value);

	}
	
	public void click(String locator) {

	/*	if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}*/
		driver.findElement(By.xpath(locator)).click();
		test.log(LogStatus.INFO, "Clicking on : " + locator);
	}








}
