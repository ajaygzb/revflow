package com.revflow.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.revflow.utilities.ExcelReader;
import com.revflow.utilities.ExtentManager;
import com.revflow.utilities.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

public class BasePage {

/**@author ajay.kumar4
 */
	
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static Properties OR = new Properties();	
	public static ExtentTest test;
	public ExtentReports rep = ExtentManager.getInstance();
    private  WebDriver driver;
    public static ExcelReader excel = new ExcelReader(
	System.getProperty("user.dir") + "\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public static String screenshotPath;
	public static String screenshotName;
	public static String browsername;
	public static String Jbrowser = System.getProperty("browser");
	
	//Saucelab env.
	
	public static final String USERNAME = "ajaygzb18";
	public static final String ACCESS_KEY = "08c76b17-4bca-4d68-bea6-8bcddf2c7d0a";
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	
	
	
	
	
	
     
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
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		log.debug("Firefox Launched !!!");
		
		
	}else if(browser.equalsIgnoreCase("chrome")){

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		log.debug("Chrome Launched !!!");
		
	}else if(browser.equalsIgnoreCase("IE")){

		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		log.debug("IE Launched !!!");
		
		
	}else if(browser.equalsIgnoreCase("cloud")){
		

	    DesiredCapabilities caps = DesiredCapabilities.chrome();
	    caps.setCapability("platform", "Windows 10");
	    caps.setCapability("version", "latest");
	 
	    try {
		  driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("IE Launched !!!");
	}
	 driver.manage().window().maximize();
	 driver.get(url);
	 log.debug("Navigated to : " + url);
	// driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
	 //wait = new WebDriverWait(driver, 5);
	 setDriver(driver);
	 getDriver();
	 
	 
 }
 
 
 
 @Parameters("browser")
 @BeforeTest
 public void beforeTest(String browser) {
	 
	 
	 
          if(browser != null && !browser.isEmpty()){
          System.out.println("Getting Browser name from TestNG XML:  "+browser);
    	  browserLaunch(browser,config.getProperty("url")); 
    	  browsername=browser;
    	  
    	  
      }else{
    	  
    	  System.out.println("Getting Browser name from config.   "+browser);
    	  browserLaunch(config.getProperty("browser"),config.getProperty("url"));   
    	  browsername=browser;
      }
	 
	 
	  
	 
	
 }
 

    @AfterSuite
	public void tearDown() {

		if (driver != null) {
			try{driver.quit();
			}
			catch(Exception e){
				
			}
		}

		log.debug("test execution completed !!!");
	}
    
    @AfterTest
  	public void Closedriver() {

  		if (driver != null) {
  			driver.close();
  		}

  		log.debug("browser closed called !!!");
  		//System.out.println("browser closed called !!!");
  	}
 
	

    
    // *******************************Common Methods*******************************
    
    
    
	public  void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.ERROR, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotName));
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



	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public   void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}

	








}
