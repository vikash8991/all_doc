package aiSensyTest;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjecct.LoginPage;


public class LoginPageTest extends BaseTest{


	//run this through master -because i did some changes in this setup
	

	@Test
	public void login() {
		//creating the object for login class because we want to access method of login
		
		logger.info("**** Starting Login test ****");
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setUsername(p.getProperty("prod_user_name"));
		lp.setPassword(p.getProperty("prod_password"));		
		lp.clickLogin();
		
		logger.info("**** clicked on Login btn ****");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard"));

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard", "Not redirected to the expected dashboard.");
		
		logger.info("**** Finished Login of Aisensy ****");
	}
	
	
	
}
