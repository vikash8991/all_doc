package day1;

import org.testng.Assert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginTest2 {
	
	//With page factory
	
	WebDriver driver;
	
	// setup
	@BeforeClass
	void setup() {
		driver=new ChromeDriver();
		driver.get("https://www.app.aisensy.com/login");
		driver.manage().window().maximize();
	}
	
	
	
	// Test case to validate login functionality
	@Test
	public void login() {
		//creating the object for login class
		LoginPage2 lp=new LoginPage2(driver);
		
		lp.setUsername("vikash.kumar");  // Enter Actual username
		lp.setPassword("vikash.kumar1");  // Enter Actual password
		lp.clickLogin();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard"));

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard", "Not redirected to the expected dashboard.");
		
		
//		
	}
	
//	@Test
//	public void logout() {
//		System.out.println("inside logout");
//		LogOut LogOutobj=new LogOut(driver);
//		LogOutobj.clcikOnProfile();
//		LogOutobj.clcikOnLogout();
//		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.urlToBe("https://www.app.aisensy.com/login"));
//
//		Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/login", "Not redirected to the expected dashboard.");
//		
//	}
	
	// Test case to invalidate login with invalid credentials
//	@Test
//    void loginWithInvalidPassword() {
//        LoginPage2 lp = new LoginPage2(driver);
//        
//        lp.setUsername("vikash.kumar");
//        lp.setPassword("wrongpassword"); // Using invalid password
//        lp.clickLogin();
//        
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Wait for error message to appear
//        WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("//div[text()='Invalid login details. Try again!']"))); // Update selector based on your HTML
//
//        // Assert that error message is displayed
//        if (errorMessage.isDisplayed() && errorMessage.getText().equals("Invalid login details. Try again!")) {
//            System.out.println("Login failed because of invalid credentials.");
//        } else {
//            System.err.println("Unexpected behavior: Error message not displayed or text mismatch.");
//        }
//
//        // Additional assertion to ensure we're still on the login page
//        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
//                "User was redirected away from login page despite invalid credentials.");
//    }
	
	//close browser
	
//	@AfterClass
//	void close() {
//		driver.quit();
//	}

}
