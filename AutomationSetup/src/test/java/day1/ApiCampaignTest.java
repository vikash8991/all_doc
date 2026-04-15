package day1;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiCampaignTest {//With page factory
	
	WebDriver driver;
   // WebDriverWait wait;
	price lp;
    
	
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
		
		lp.setUsername("testjulyprod@mailinator.com");      // Enter actual credentials
        lp.setPassword("12345@A12");  // Enter Actual password
		lp.clickLogin();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard"));

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard", "Not redirected to the expected dashboard.");
	}
	
	@Test(priority=2)
	void selectProject() throws InterruptedException {

        lp.selectproject();

        Thread.sleep(2000);
    }
    
	
	
	
	@Test(priority=3)
	void campCreation() throws InterruptedException {
		ApiCampaign ApiCampObj=new ApiCampaign(driver);
		
		String uniqueCampaign=generateUniqueCampaignName();
		
		System.out.println("Clicking on Campaign button...");
		Thread.sleep(2000);
		ApiCampObj.clickCampaign();
		
		
		System.out.println("Clicking on Launch Campaign button...");
		Thread.sleep(2000);
		ApiCampObj.launchButton();
		
		
		
		
		System.out.println("Clicking on Api Campaign button...");
		Thread.sleep(2000);
		ApiCampObj.clickOnApiCampaign();
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(ApiCampObj.txt_campaignName));
		
		
		//set unique api name
		System.out.println("creating Api Campaign name...");
		Thread.sleep(2000);
		ApiCampObj.setApiCampaignName(uniqueCampaign);
		
		
		Thread.sleep(2000);
		ApiCampObj.selectTemp("test345");
		
		ApiCampObj.clickOnSetLive();
		
		
	}

}
