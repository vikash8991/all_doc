package aiSensyTest;

import org.testng.Assert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import aiSensy.PageObject.LoginPage;
import aiSensy.PageObject.LoginWithAssertion;

public class LoginWithAssertionTest {
	
	WebDriver driver;
    LoginPage loginPage;

    // Setup WebDriver and the LoginPage
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();  // Initialize WebDriver (e.g., ChromeDriver)
        driver.get("https://www.app.aisensy.com/login");  // Replace with your login URL
        
    }

    // Test method for valid login
    @Test
    public void testValidLogin() {
    	LoginWithAssertion loginPage  = new LoginWithAssertion(driver);  // Initialize the Page Object
        loginPage.enterUsername("vikash.kumar");  // Replace with valid username
        loginPage.enterPassword("vikash.kumar1");  // Replace with valid password
        loginPage.clickLoginButton();              // Click the login button

        // Assert that the dashboard is displayed after successful login
        //Assert.assertTrue("Dashboard should be visible after login", loginPage.isDashboardVisible());
    }

    // Test method for invalid login
    @Test
    public void testInvalidLogin() {
    	LoginWithAssertion loginPage  = new LoginWithAssertion(driver);  // Initialize the Page Object
        loginPage.enterUsername("invalidUsername");  // Replace with invalid username
        loginPage.enterPassword("invalidPassword");  // Replace with invalid password
        loginPage.clickLoginButton();                // Click the login button

        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement actualMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Invalid login details. Try again!']")));
        
     // Retrieve and verify the error message
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Invalid login details. Try again!";
        
        

        
        // Validate using assertion
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message should match the expected text");


        if (actualMsg.isDisplayed()) {
            System.out.println("Test case passed: " + actualMsg.getText());
        } else {
            System.out.println("Test case failed: Error message not displayed.");
        }
    }

    // Teardown method to close the browser
//    public void tearDown() {
//        driver.quit();  // Close the browser
//    }

}
