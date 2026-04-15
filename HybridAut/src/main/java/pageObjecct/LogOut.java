package pageObjecct;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.BasePage;

public class LogOut extends BasePage{
	

	WebDriver driver;
	
	//constructor calling base class
	public LogOut(WebDriver driver){
		
		super(driver);
		
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
