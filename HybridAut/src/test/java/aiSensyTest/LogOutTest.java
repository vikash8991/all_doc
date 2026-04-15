package aiSensyTest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjecct.LogOut;
import pageObjecct.LoginPage;

public class LogOutTest extends BaseTest{

		//  Test case to validate login functionality
		@Test
		public void login() {
			//creating the object for login class
			LoginPage lp=new LoginPage(driver);
			
			lp.setUsername("vikash.kumar");  // Enter Actual username
			lp.setPassword("vikash.kumar");  // Enter Actual password
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

}
