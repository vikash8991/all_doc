package day1;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ForgotPasswordTest {
	
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
//		@Test
//		public void login() {
//			//creating the object for login class
//			LoginPage2 lp=new LoginPage2(driver);
//			
//			lp.setUsername("vikash.kumar");  // Enter Actual username
//			lp.setPassword("vikash.kumar");  // Enter Actual password
//			lp.clickLogin();
//			
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.until(ExpectedConditions.urlToBe("https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard"));
//
//			Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard", "Not redirected to the expected dashboard.");
//		}
		
		
		// Test case to validate forgot functionality
		@Test
		public void forgot() throws InterruptedException {
				//creating the object for login class
				ForgotPassword fp=new ForgotPassword(driver);
				fp.clcikOnForgot();
				fp.setForgotEmail("vikash.kumar");
				fp.clcikOnSearchAccount();
				
				Thread.sleep(2000);
				fp.clcikOnSendOtp();
				
				Thread.sleep(2000);
				fp.setNewPass("12345Aqwe@");
				
				fp.setCnfPass("12345Aqwe@");
				
				fp.clcikReset();
					
		}

}
