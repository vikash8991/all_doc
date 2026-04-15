package day1;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FBDropdownTest {
	
	//With page factory
	
		WebDriver driver;
		
		
		
		// setup
		@BeforeClass
		void setup() {
			driver=new ChromeDriver();
			//driver.get("https://www.app.aisensy.com/signup");
			driver.get("https://business.facebook.com/direct-support/?business_id=1215672488927258&call_site=source_direct_support_tabs_switch\".");
			
			driver.manage().window().maximize();
		}
		
		//test method
		
		@Test
		void FBDropdown() throws InterruptedException {
			
			
			

			//creating the object for login class
			FBDropdown sp=new FBDropdown(driver);
			sp.clickOnFB();
			Thread.sleep(2000);
			
			// Switch to new window
			String mainWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();

			for (String window : allWindows) {
			    if (!window.equals(mainWindow)) {
			        driver.switchTo().window(window);
			    }
			}
			
			Thread.sleep(1800);
			sp.clickOnEmail("918709077106");
			
			Thread.sleep(2000);
			sp.clickOnPass("fN#mLGUgTk!mN42");
			
			Thread.sleep(1500);
			//sp.click_on_login();
			
			







//			sp.setFullName("vikash");
//			
//			sp.setEmail("test10jun1@mailinator.com");
//			//sp.setEmail(uniqueEmail);
//			sp.setContact("8279396355");
//			//sp.setContact("8708680186");
//			sp.setPass("12345@A12");
//			sp.setConfPass("12345@A12");
//			sp.clickFreeTrail();
			
			
			

			
		}

}
