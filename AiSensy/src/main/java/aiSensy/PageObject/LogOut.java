package aiSensy.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOut {
	
		//page object class
		//With page factory
		// we can add more element and method as per our uses
		
		WebDriver driver;
		
		//constructor
		
		public LogOut(WebDriver driver){
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		
		
		
		//locator, not need find element & find elements
		
		@FindBy(xpath="/html/body/div[1]/div/div[1]/div[3]/div/div") 
		WebElement profile;
		
		
		@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div[3]/button/span[1]") 
		WebElement logout;
		
		

		
		
		//action method
		
		//method to click on profile
		public void clcikOnProfile() {
			profile.click();
			
		}
		
		//Method to click on
		public void clcikOnLogout() {
			logout.click();
			
		}
		


}
