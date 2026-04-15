package day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FBDropdown {


	//page object class
	//With page factory
	// we can add more element and method as per our uses
	
	WebDriver driver;
	
	//constructor
	
	FBDropdown(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(xpath="/html/body/div/div[1]/div/div[1]/div/div/div/div/div[2]/div/div/div/div[3]/div/div/div/div[1]/div[2]/span/span") 
	WebElement click_on_fb;
	
	
	public void clickOnFB() {
		click_on_fb.click();
		
	}
	
	
	@FindBy(name="email") 
	WebElement click_on_email;
	
	
	public void clickOnEmail(String email) {
		click_on_email.sendKeys(email);
		
	}
	
	
	@FindBy(xpath="/html/body/div[1]/div[1]/div[1]/div/div[3]/div[2]/form/div[2]/div[2]/div/div/input") 
	WebElement click_on_pass;
	
	public void clickOnPass(String pass) {
		click_on_pass.sendKeys(pass);
		
	}
	
	
	@FindBy(name="login") 
	WebElement click_on_login;
	
	public void click_on_login() {
		click_on_login.click();
		
	}
	
	//locator, not need find element & find elements
	
	
	@FindBy(xpath="//input[@id='name']") 
	WebElement txt_FullName;
	
	@FindBy(xpath="//input[@id='email']") 
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='contact']") 
	WebElement txt_contact;
	
	@FindBy(xpath="//input[@id='password']") 
	WebElement txt_pass;
	
	@FindBy(xpath="//input[@id='confirmPassword']") 
	WebElement txt_confPass;
	
	@FindBy(css="[id=\"start-free-trial-button\"]") 
	WebElement btn_FreeTrail;
	
	



	@FindBy(id="company") 
	WebElement txt_companyName;
	
			
	@FindBy(id = "companyDescription")
    WebElement txt_companyDesc;
	

	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[3]/div/div[6]/div/div/input")
	WebElement StaticDropDown_State;
	
	

	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[3]/div/div[7]/div/div/input") 
	WebElement StaticDropDown_companySize;
	
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[3]/div/div[8]/div/div/input") 
	WebElement StaticDropDown_industries;
	
	
	@FindBy(xpath= "//span[normalize-space()='Complete Sign Up']") 
	WebElement btn_submit;
	
	
	
	
	
	
	//action method
	
	public void setFullName(String FullName) {
		txt_FullName.sendKeys(FullName);
		
	}
	
	public void setEmail(String email) {
		txt_email.sendKeys(email);
		
	}
	
	
	public void setContact(String mob_num) {
		txt_contact.sendKeys(mob_num);
		
	}
	
	public void setPass(String pass) {
		txt_pass.sendKeys(pass);
		
	}
	
	public void setConfPass(String confPass) {
		txt_confPass.sendKeys(confPass);
		
	}
	
	public void clickFreeTrail() {
		btn_FreeTrail.click();
		
	}
	
	//after otp need to continue signup, i might have to use some wait
	public void setCompanyName(String CompanyName) {
		txt_companyName.sendKeys(CompanyName);
		
	}

	
	public void setcompanyDesc(String companyDesc) {
		txt_companyDesc.sendKeys(companyDesc);
		
	}
	

	
	public void selectState(String State) {
		//Select countrySelect = new Select(StaticDropDown_State);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		StaticDropDown_State.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Bihar']")));
        driver.findElement(By.xpath("//li[normalize-space()='Bihar']")).click();  // Select "Bihar" option
	}
	
	public void selectSize(String Size) {
		//Select countrySelect = new Select(StaticDropDown_companySize);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		StaticDropDown_companySize.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='1 - 10 Employees']")));
        driver.findElement(By.xpath("//li[normalize-space()='1 - 10 Employees']")).click(); 
		
		
	}
	
	public void selectIndustries(String industries) {
		StaticDropDown_industries.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='IT Services']")));
	    driver.findElement(By.xpath("//li[normalize-space()='IT Services']")).click(); 
		
	}
	
	public void clickSubmit() {
		btn_submit.click();
		
	}
}
