package day1;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassword {
	
			//page object class
			//With page factory
			// we can add more element and method as per our uses
			
			WebDriver driver;
			
			//constructor
			
			ForgotPassword(WebDriver driver){
				this.driver=driver;
				PageFactory.initElements(driver, this);
			}
			
			
			
			
			//locator, not need find element & find elements
			
			@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[7]/span") 
			WebElement forgot;
			
			
			
			@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div/input") 
			//@FindBy(name="name=\"forgottenDetail\"") 
			WebElement txt_forgotDetail;
			
			
			@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/button/span[1]") 
			WebElement btn_searchAccount;
			
			
			
			@FindBy(xpath="//span[@class='MuiButton-label']")
			WebElement btn_sendOtp;
			
			@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div[1]/div/input")
			WebElement txt_newPass;
			
			
			@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div[3]/div/input")
			WebElement txt_cnfPass;
			
			
			
			@FindBy(xpath="//span[@class='MuiButton-label']")
			WebElement btn_reset;
			
//			@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[2]/div/div/div[3]/div/div[1]/div[3]/button/span[1]") 
//			WebElement logout;
			
			
			
//			@FindBy(xpath="//input[@id='password']") 
//			WebElement txt_pass;
//			
//			@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div/button/span[1]/h5") 
//			WebElement btn_login;
			
			
			//action method
			
			//method to click on forgot password
			public void clcikOnForgot() {
				forgot.click();
				
			}
			
			public void setForgotEmail(String email) {
				txt_forgotDetail.click();
				txt_forgotDetail.sendKeys(email);
				
			}
			
			//method to click on forgot password
			public void clcikOnSearchAccount() {
				btn_searchAccount.click();
				
			}
			
			//method to click on forgot password
			public void clcikOnSendOtp() {
				btn_sendOtp.click();
				
			}
			
			
//			//method to set new pass password
			public void setNewPass(String newpass) {
				//txt_newPass.click();
				
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.elementToBeClickable(txt_newPass));
				txt_newPass.click();
				
				txt_newPass.sendKeys(newpass);
				
			}
			
			
			
			
			//method to set conform pass password
			public void setCnfPass(String cnfPass) {
				txt_cnfPass.click();
				txt_cnfPass.sendKeys(cnfPass);
				
			}
			
			//method to click on reset btn
			public void clcikReset() {
				btn_reset.click();
				
			}
			
			

}
