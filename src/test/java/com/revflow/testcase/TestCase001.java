package com.revflow.testcase;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.testng.SkipException;
import org.testng.annotations.Test;
import com.revflow.base.BasePage;
import com.revflow.page.HomePage;
import com.revflow.page.Login;
import com.revflow.utilities.TestUtil;



public class TestCase001 extends BasePage {

	public Login lp;
	public HomePage hp;
	
	Map<String,String> map = new HashMap<String, String>();
	
	@Test(description = "To Verify Valid Login in R6 Application",
		      dataProviderClass=TestUtil.class,dataProvider="dp")
 public void LoginTest(Hashtable<String,String> data) throws IOException {
		
if(!data.get("runmode").equals("Y")){
			
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}		
 map.put("USERNAME",data.get("username"));
 map.put("PASSWORD",data.get("password"));
 lp = new Login(driver,map);
 lp.verifyPageLoaded();
 lp.verifyPageUrl();
 verifyEquals("Forgot password ?",lp.GetForgotPasswordLinkText());
 lp.fillAndSubmit();
// Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),"Login not successful");
log.debug("Login successfully executed");
	  
  }
	
	
	@Test(priority=2,description = "LogOut from R6 Application")
	public void LogOut_Test(){
		
		hp = new HomePage(driver);
		hp.clickAdminLink();
		hp.clickArFollowupLink();
		hp.clickBillingLink();
		hp.clickChargesLink();
		hp.clickDenialMgmtLink();
		hp.clickLogowebptLink();
		hp.clickPatientLink();
		hp.clickPatientLink();
		hp.clickPaymentsLink();
		hp.clickReportsLink();
		hp.verifyPageLoaded();
		hp.verifyPageUrl();
		hp.clickDropdownLink();
		hp.clickLogOutLink();
	
	}

 
}
