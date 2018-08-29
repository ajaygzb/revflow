# revflow
Data driven test
Selenium sample framework with Extent report using Page Object Model.

Plugin: Selenium Page Object Generator,Xpath Helper,X path helper wizard.
To enable mail enable code in Listerners class


// To launch a default firefox profile use:

  	   WebDriverManager.firefoxdriver().setup();
 
            // Create object of ProfilesIni class
 
            ProfilesIni init=new ProfilesIni();
 
 
 
            // Get the default session  
 
            FirefoxProfile profile=init.getProfile("default");
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability(FirefoxDriver.PROFILE,profile);
            options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            WebDriver driver=new FirefoxDriver(options);
 
 
 
            driver.get("");
 
 
 
            driver.quit();

