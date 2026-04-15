package day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApiCampaign {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    
	//constructor
		
	public ApiCampaign(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	



	//locator

	@FindBy(xpath = "//*[name()='path' and contains(@d,'M2.5 19h19')]")
	private WebElement click_campaign;
	
//	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[1]/div[3]/div/button[1]/span[1]")
	
	@FindBy(xpath = "//span[text()='Launch']")
	private WebElement click_Launch;
	
	
	@FindBy(xpath = "//button[contains(@name, 'button-next-API Campaign')]")
	WebElement btn_Api_Camp;
	
	
	
	//text box campaign name
	
	@FindBy(xpath = "//input[@placeholder=\"Enter campaign name\"]")
	WebElement txt_campaignName;
	
	
	//select template name
	//@FindBy(xpath = "//input[@name=\"templateName\"]")
	By select_template_name=By.xpath("//input[@placeholder=\"Search template\"]");
	//private WebElement select_template_name;
	
	
	//click on set live button
	
	@FindBy(xpath = "//span[text()=\"Set Live\"]")
	WebElement btn_set_Live;
	
	
	
	//verify 
	
	@FindBy(xpath = "//h5[text()=\"Yay, Campaign has been sucessfully created\"]")
	WebElement verify_msg;
	
	

	
	

	//action method/
	
		// Method to click the "campaign" button
	    public void clickCampaign() {
	    	click_campaign.click();
	    }



		// Method to click the "launch" button
	    public void launchButton() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        wait.until(ExpectedConditions.elementToBeClickable(click_Launch));
	        click_Launch.click();
	    }
	    
	    
		
		// Method to click the "Next" button
	    public void clickOnApiCampaign() {
	    	
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement apiCampaignButton = wait.until(ExpectedConditions.elementToBeClickable(btn_Api_Camp));
	    	apiCampaignButton.click();


//	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    	List<WebElement> nextButtons = wait.until(ExpectedConditions
//	    	    .numberOfElementsToBeMoreThan(By.xpath("//span[text()='Next']"), 1));
//
//	    	nextButtons.get(1).click();  // Click 2nd "Next"
//    	
	    }
	    
	    //select template
	    public void selectTemp(String query) {
	    	
	    	
	    	 // Set up WebDriverWait
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Locate the search input element (modify 'select_template_name' as needed)
	        WebElement searchInput = driver.findElement(select_template_name);

	        // Scroll the element into view using JavaScript
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", searchInput);

	        // Wait until the input element is visible and interactable
	        wait.until(ExpectedConditions.visibilityOf(searchInput));

	        // Perform the input actions
	        searchInput.clear();  // Clear any existing text if necessary
	        searchInput.sendKeys(query + Keys.ENTER);  // Type the query and press ENTER to search

	        // Use ARROW_DOWN to navigate through the dropdown options and select the first option
	        searchInput.sendKeys(Keys.ARROW_DOWN);
	        searchInput.sendKeys(Keys.ENTER);  // Confirm selection with ENTER
	    	
	   
	    }
	    
	    
	    public void setApiCampaignName(String campName) {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement createCampaign = wait.until(ExpectedConditions.elementToBeClickable(txt_campaignName));

	        // Click and send the campaign name to the input field
	        createCampaign.click();
	        createCampaign.clear();  // Ensure the field is cleared before entering a new name (optional, but good practice)
	        createCampaign.sendKeys(campName);
	    }
	    
	    
	    
	    
	    public void clickOnSetLive() {
	    	btn_set_Live.click();
	    	
	    }
	    
	    


}
