package aiSensy.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginWithAssertion {
	
	 WebDriver driver;

	    // Define locators for the login elements using @FindBy
	 	@FindBy(xpath="//input[@id='email']")
	    WebElement usernameField;

	 	@FindBy(xpath="//input[@id='password']")
	    WebElement passwordField;

	 	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div/button/span[1]/h5")
	    WebElement loginButton;

	    @FindBy(xpath = "//div[text()=\"Invalid login details. Try again!\"]")
	    WebElement errorMessage;



	    // Constructor to initialize the PageFactory
	    public LoginWithAssertion(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);  // Initialize the elements
	    }

	    // Method to enter username
	    public void enterUsername(String username) {
	        usernameField.sendKeys(username);
	    }

	    // Method to enter password
	    public void enterPassword(String password) {
	        passwordField.sendKeys(password);
	    }

	    // Method to click the login button
	    public void clickLoginButton() {
	        loginButton.click();
	    }

	    // Method to get the error message
	    public String getErrorMessage() {
	        return errorMessage.getText();
	    }

	    

}
