package com.revflow.page;
import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(css = "a.text-right.cursor_point")
    @CacheLookup
    private WebElement forgotPassword;

    private final String pageLoadedText = "";

    private final String pageUrl = "/login";

    @FindBy(name = "Password")
    @CacheLookup
    private WebElement password;

    @FindBy(css = "button.ui.primary.button.success_btn")
    @CacheLookup
    private WebElement signIn;

    @FindBy(name = "Username")
    @CacheLookup
    private WebElement username;

    public Login() {
    	
    	
    }

    public Login(WebDriver driver) {
        this();
        this.driver = driver;
        PageFactory.initElements(driver,this);	
        
        
    }

    public Login(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
       
    }

    public Login(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on Forgot Password Link.
     *
     * @return the Login class instance.
     */
    public Login clickForgotPasswordLink() {
        forgotPassword.click();
        return this;
    }
    
    public String GetForgotPasswordLinkText() {
    	return forgotPassword.getText();
       
    }

    /**
     * Click on Sign In Button.
     *
     * @return the Login class instance.
     */
    public Login clickSignInButton() {
        signIn.click();
        return this;
    }

    /**
     * Fill every fields in the page.
     *
     * @return the Login class instance.
     */
    public Login fill() {
        setUsernameTextField();
        setPasswordPasswordField();
        return this;
    }

    /**
     * Fill every fields in the page and submit it to target page.
     *
     * @return the Login class instance.
     */
    public Login fillAndSubmit() {
        fill();
        return submit();
    }

    /**
     * Set default value to Password Password field.
     *
     * @return the Login class instance.
     */
    public Login setPasswordPasswordField() {
        return setPasswordPasswordField(data.get("PASSWORD"));
    }

    /**
     * Set value to Password Password field.
     *
     * @return the Login class instance.
     */
    public Login setPasswordPasswordField(String passwordValue) {
        password.sendKeys(passwordValue);
        return this;
    }

    /**
     * Set default value to Username Text field.
     *
     * @return the Login class instance.
     */
    public Login setUsernameTextField() {
    	
        return setUsernameTextField(data.get("USERNAME"));
    }

    /**
     * Set value to Username Text field.
     *
     * @return the Login class instance.
     */
    public Login setUsernameTextField(String usernameValue) {
        username.sendKeys(usernameValue);
        return this;
    }

    /**
     * Submit the form to target page.
     *
     * @return the Login class instance.
     */
    public Login submit() {
        clickSignInButton();
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the Login class instance.
     */
    public Login verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	System.out.println(pageLoadedText);
                return d.getPageSource().contains(pageLoadedText);
                
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the Login class instance.
     */
    public Login verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}
