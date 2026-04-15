package aiSensy.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import aiSensy.AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	
	//constructor
	public LoginPage(WebDriver driver){
		
		// i am sending driver to parent method Abstract
		//every child class need to follow this
		super(driver);
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
