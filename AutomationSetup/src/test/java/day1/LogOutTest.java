package day1;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogOutTest {
	
	//With page factory
	
		WebDriver driver;
		
		// setup
		@BeforeClass
		void setup() {
			driver=new ChromeDriver();
			driver.get("https://www.app.aisensy.com/login");
			driver.manage().window().maximize();
		}
		
		
    	

		//  Test case to validate login functionality
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
		}
		
		// Test case to validate login functionality
		@Test
		public void logout() {
			LogOut LogOutobj=new LogOut(driver);
			LogOutobj.clcikOnProfile();
			LogOutobj.clcikOnLogout();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.urlToBe("https://www.app.aisensy.com/login"));

			Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/login", "Not redirected to the expected dashboard.");
			
		}
		
		// Test case to validate login with invalid credentials
//		@Test
//	    public void testLoginWithInvalidCredentials() {
//	        // Creating the object for the LoginPage2 class (Page Object Model)
//	        LoginPage2 lp = new LoginPage2(driver);
//	        
//	        // Entering the actual username and invalid password
//	        lp.setUsername("vikash.kumar");
//	        lp.setPassword("vikash.kumar1");  // Invalid password
//	        lp.clickLogin();
//	
//	        // Wait for a potential error message or the URL change (adjust depending on the behavior after failed login)
//	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	        wait.until(ExpectedConditions.urlToBe("https://www.app.aisensy.com/login"));
//	
//	        // Assuming a failed login redirects back to the login page
//	        Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/login", "Not redirected to the login page after invalid login.");
//	    }
		
		//close browser
		
//		@AfterClass
//		void close() {
//			driver.quit();
//		}

}
