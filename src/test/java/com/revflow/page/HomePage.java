package com.revflow.page;
import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(css = "#sidebar-wrapper div.ui.secondary.vertical.menu div:nth-of-type(8) a")
    @CacheLookup
    private WebElement admin;

    @FindBy(css = "#sidebar-wrapper div.ui.secondary.vertical.menu div:nth-of-type(6) a")
    @CacheLookup
    private WebElement arFollowup;

    @FindBy(css = "#sidebar-wrapper div.ui.secondary.vertical.menu div:nth-of-type(3) a")
    @CacheLookup
    private WebElement billing;

    @FindBy(css = "#sidebar-wrapper div.ui.secondary.vertical.menu div:nth-of-type(2) a")
    @CacheLookup
    private WebElement charges;

    @FindBy(css = "#sidebar-wrapper div.ui.secondary.vertical.menu div:nth-of-type(5) a")
    @CacheLookup
    private WebElement denialMgmt;

    @FindBy(xpath = "//div[@class='tranparentSpace']")
    @CacheLookup
    private WebElement logowebpt;

    private final String pageLoadedText = "";

    private final String pageUrl = "/R6/coming_soon";

    @FindBy(css = "#sidebar-wrapper div.ui.secondary.vertical.menu div:nth-of-type(1) a")
    @CacheLookup
    private WebElement patient;

    @FindBy(css = "#sidebar-wrapper div.ui.secondary.vertical.menu div:nth-of-type(4) a")
    @CacheLookup
    private WebElement payments;

    @FindBy(css = "#sidebar-wrapper div.ui.secondary.vertical.menu div:nth-of-type(7) a")
    @CacheLookup
    private WebElement reports;
    
    @FindBy(xpath = "(//div[@class='ui dropdown']//i[@class='dropdown icon'])[1]")
    @CacheLookup
    private WebElement dropdown;
    
    @FindBy(xpath = "(//div[@class='item'][5])[1]")
    @CacheLookup
    private WebElement logout;
    
    

    public HomePage() {
    }

    public HomePage(WebDriver driver) {
        this();
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public HomePage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public HomePage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on Admin Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickAdminLink() {
        admin.click();
        return this;
    }

    /**
     * Click on Ar Followup Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickArFollowupLink() {
        arFollowup.click();
        return this;
    }

    /**
     * Click on Billing Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickBillingLink() {
        billing.click();
        return this;
    }

    /**
     * Click on Charges Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickChargesLink() {
        charges.click();
        return this;
    }

    /**
     * Click on Denial Mgmt Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickDenialMgmtLink() {
        denialMgmt.click();
        return this;
    }

    /**
     * Click on Logowebpt Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickLogowebptLink() {
        logowebpt.click();
        return this;
    }

    /**
     * Click on Patient Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickPatientLink() {
        patient.click();
        return this;
    }

    /**
     * Click on Payments Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickPaymentsLink() {
        payments.click();
        return this;
    }

    /**
     * Click on Reports Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickReportsLink() {
        reports.click();
        return this;
    }
    
    /**
     * Click on Dropdown Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickDropdownLink() {
        dropdown.click();
        return this;
    }
    
    
    /**
     * Click on Logout Link.
     *
     * @return the HomePage class instance.
     */
    public HomePage clickLogOutLink() {
        logout.click();
        return this;
    }
    
    
    
    
    
    

    /**
     * Verify that the page loaded completely.
     *
     * @return the HomePage class instance.
     */
    public HomePage verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the HomePage class instance.
     */
    public HomePage verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}
