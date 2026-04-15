package day1;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CampTest {
	//With page factory
	
		WebDriver driver;
		
		private String generateUniqueCampaignName() {
		    String uniqueID = UUID.randomUUID().toString().substring(0, 6); // Short unique ID
		    return "Campaign" + "_" + uniqueID;
		}
		
		// setup
		@BeforeClass
		void setup() {
			driver=new ChromeDriver();
			driver.get("https://www.app.aisensy.com/login");
			driver.manage().window().maximize();
		}
		
		//test method
		
		@Test
		void login() {
			//creating the object for login class
			LoginPage2 lp=new LoginPage2(driver);
			
			lp.setUsername("vikash.kumar");  // Enter Actual username
			lp.setPassword("vikash.kumar1");  // Enter Actual password
			lp.clickLogin();
			
			
			//invalid alert i need to assert
			////div[@class='MuiAlert-message']
			
//			Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard", "Not redirected to the expected dashboard.");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.urlToBe("https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard"));

			Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard", "Not redirected to the expected dashboard.");
		}
		
		
		
		@Test(dependsOnMethods = "login")
		void campCreation() throws InterruptedException {
			Camp ct=new Camp(driver);
			
//			String uniqueCampaign=generateUniqueCampaignName();
	        String uniqueCampaignName = "Campaign_" + UUID.randomUUID().toString().substring(0, 6);

			
			Thread.sleep(2000);
			ct.clickCampaign();
			
			Thread.sleep(2000);
			ct.launchButton();
			
			Thread.sleep(2000);
			ct.clickNext();
			
			Thread.sleep(2000);
			ct.setCampaignName(uniqueCampaignName);
			
			Thread.sleep(2000);
			ct.clickNextAfterCampaignName();
			
			Thread.sleep(2000);
			ct.setTagName();
			
			
			Thread.sleep(2000);
			ct.setTagValue();
			
			Thread.sleep(2000);
			ct.clickApply();
			
			Thread.sleep(2000);
			ct.clickNextAfterTagName();
			
			Thread.sleep(2000);
			ct.searchTemp("test345");
			
			
			Thread.sleep(2000);
			ct.clickNextAfterSelectingTemplate();
			
			Thread.sleep(2000);
			ct.clickNextAfterSelectingTemplate1();
			
			Thread.sleep(2000);
			ct.clickFinalSend();
			
		}


}
