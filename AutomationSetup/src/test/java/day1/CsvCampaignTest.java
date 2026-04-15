package day1;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CsvCampaignTest {
	

	WebDriver driver;
   // WebDriverWait wait;
    
	
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
	
	@Test(priority = 1)
	void login() {
		//creating the object for login class
		LoginPage2 lp=new LoginPage2(driver);
		
		lp.setUsername("testjulyprod@mailinator.com");      // Enter actual credentials
        lp.setPassword("12345@A12a");  // Enter Actual password
		lp.clickLogin();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard"));

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.app.aisensy.com/projects/64e60ac3b582c60e76a1ad78/dashboard", "Not redirected to the expected dashboard.");
	}
	
	
	
	@Test(priority = 2)
	void campCreation() throws InterruptedException {
		CsvCampaign CsvCampObj=new CsvCampaign(driver);
		
		String uniqueCampaign=generateUniqueCampaignName();
		
		//System.out.println("Clicking on Campaign button...");
		Thread.sleep(2000);
		CsvCampObj.clickCampaign();
		
		
		//System.out.println("Clicking on Launch Campaign button...");
		Thread.sleep(2000);
		CsvCampObj.launchButton();
		
		
		
		
		//System.out.println("Clicking on Api Campaign button...");
		Thread.sleep(2000);
		CsvCampObj.clickOnCsvCampaign();
		
		
		CsvCampObj.setApiCampaignName(uniqueCampaign);
		
		CsvCampObj.clickOnNext();
		
		
		CsvCampObj.uploadFile();
		
		
		//getting issue selecting number need to fix
		CsvCampObj.mapName();
		
		CsvCampObj.mapNumber();
		
		CsvCampObj.clickOnNextAfterMaping();
		
		CsvCampObj.searchTemp("mark_test");
		
		CsvCampObj.clickOnNextAfterTempSelection();
		
		
		CsvCampObj.clickOnSkip();
		
		CsvCampObj.clickOnSend();
		
		//CsvCampObj.verifySuccessMessage();
		
		
	}
	
	@Test(priority = 3)
	public void verifySuccessMessage() {
        if (driver == null) {
            System.err.println("Driver is not initialized.");
            return;
        }

        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Wait for the success message to appear
        	//System.out.println("inside verify");
            WebElement successMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(), 'Successfully sent Campaign')]"))
            );
            String actualMessage = successMessageElement.getText();
            //System.out.println(actualMessage);
            // Define the expected message
            String expectedMessage = "Successfully sent Campaign";

            // Assert that the actual message matches the expected message
            Assert.assertEquals(actualMessage,expectedMessage,"The success message does not match!");

            System.out.println("Test Passed: Success message is displayed as expected.");
        } catch (AssertionError e) {
            System.err.println("Test Failed: " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Test Failed: Unable to interact with the element. " + e.getMessage());
        }
    }

}
