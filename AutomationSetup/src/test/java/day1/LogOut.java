package day1;

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
		
		LogOut(WebDriver driver){
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		
		
		
		//locator, not need find element & find elements
		
		@FindBy(xpath="/html/body/div[1]/div/div[1]/div[3]/div/div") 
		WebElement profile;
		
		
		@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div[3]/button/span[1]") 
		WebElement logout;
		
		
		
//		@FindBy(xpath="//input[@id='password']") 
//		WebElement txt_pass;
//		
//		@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div/button/span[1]/h5") 
//		WebElement btn_login;
		
		
		//action method
		
		//method to click on profile
		public void clcikOnProfile() {
			profile.click();
			
		}
		
		//Method to click on
		public void clcikOnLogout() {
			logout.click();
			
		}
		
//		public void setPassword(String pass) {
//			txt_pass.sendKeys(pass);
//			
//		}
//		
//		public void clickLogin() {
//			btn_login.click();
//			
//		}

}
