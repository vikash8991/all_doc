package day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	//page object class
	//Without page factory
	// we can add more element and method as per our uses
	
	WebDriver driver;
	
	//constructor
	
	LoginPage(WebDriver driver){
		this.driver=driver;
	}
	
	
	//locator
	
	By txt_username=By.xpath("//input[@id='email']");
	By txt_pass=By.xpath("//input[@id='password']");
	By btn_login=By.xpath("//h5[text()='Continue']");

	//action method
	
	public void setUsername(String user) {
		driver.findElement(txt_username).sendKeys(user);
		
	}
	
	public void setPassword(String pass) {
		driver.findElement(txt_pass).sendKeys(pass);
		
	}
	
	public void clickLogin() {
		driver.findElement(btn_login).click();
		
	}


}
