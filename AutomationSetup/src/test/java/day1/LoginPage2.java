package day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2 {
	
	//page object class
	//With page factory
	// we can add more element and method as per our uses
	
	WebDriver driver;
	
	//constructor
	
	LoginPage2(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	//locator, not need find element & find elements
	
	@FindBy(xpath="//input[@id='email']") 
	WebElement txt_username;
	
	@FindBy(xpath="//input[@id='password']") 
	WebElement txt_pass;
	
	@FindBy(xpath="//h5[text()='Continue']") 
	WebElement btn_login;
	
	
	
//	@FindBy(xpath="//div[text()='Invalid login details. Try again!']") 
//	WebElement error_Message;
	
	//action method
	
	public void setUsername(String user) {
		txt_username.sendKeys(user);
		
	}
	
	public void setPassword(String pass) {
		txt_pass.sendKeys(pass);
		
	}
	
	public void clickLogin() {
		btn_login.click();
		
	}


}
