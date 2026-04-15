package day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuperAdminLogin {
	
WebDriver driver;
	
	//constructor
	
	SuperAdminLogin(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	//locator, not need find element & find elements
	
	@FindBy(xpath="//input[@id='email']") 
	WebElement txt_username;
	
	@FindBy(xpath="//input[@id='password']") 
	WebElement txt_pass;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div/button/span[1]/h5") 
	WebElement btn_login;
	

	
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
