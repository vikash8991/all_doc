package day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Camp {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	//constructor
		
	public Camp(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	



	//locator
	By click_campaign=By.xpath("//*[name()='path' and contains(@d,'M2.5 19h19')]");
	By click_Launch=By.xpath("//*[@id=\"route-container\"]/div/div[1]/div[3]/div/button[1]/span[1]");
	By btn_next=By.xpath("/html/body/div[8]/div[3]/div/div[2]/div/div[1]/a/div/div[3]/button/span");
			
	By txt_campaignName=By.name("campaignName");
	
	//after camp name next button
	By btn_campaignNameNext=By.xpath("//span[contains(text(),'Next')]");

	//filter by attribute
	//select tag
	//value="Tags"
	
	@FindBy(css = "input[placeholder='Select Attribute']")
	private WebElement select_tag;
	
	//select value of tag in filter
	//	By select_tag_value=By.xpath("//input[@placeholder=\"Attribute Value\"]");

	@FindBy(css = "input[placeholder='Attribute Value']")
	private WebElement select_tag_value;
	
	
	//apply filter
	
	By apply_filter=By.xpath("//span[contains(text(),'Apply')]");
			
	//24hr filter
//	By click_24hrFilter=By.xpath("//span[normalize-space()='In 24hr']");
	
//	
//	By btn_apply=By.xpath("//span[normalize-space()='Apply']");
	
	
	
	By btn_nextAfterFilter=By.xpath("//span[contains(text(),'Next')]");

	
	//search template name and select
	By txt_search_filter=By.xpath("//input[@name='templateName']");
	
	// next after selecting template
//	@FindBy(xpath = "//span[text()=\"Next\"]")
	
	
	@FindBy(xpath = "//span[text()='Next']")
	private WebElement btnNextAfterSelectingTemplates;
	
	  
	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[3]/div/div[2]/div[4]/div[2]/button/span[1]")
	private WebElement btnNextAfterSelectingTemplates1;
	

	
	@FindBy(xpath = "//span[contains(text(),'SEND NOW')]")
	private WebElement btnFinalSend;
	
	//driver.findElement(By.xpath("(//div[@placeholder='Type or select an attribute'])[1]"));
	//driver.findElement(By.xpath("(//div[@placeholder='Type or select an attribute'])[2]"));
			

	@FindBy(xpath = "(//div[@placeholder='Type or select an attribute'])[1]")
	private WebElement firstParameter;
	
	
	@FindBy(xpath = "(//div[@placeholder='Type or select an attribute'])[2]")
	private WebElement secondParameter;
	
	
	//placeholder="Type or select an attribute"
	@FindBy(xpath = "//div[@placeholder='Type or select an attribute']")
	private WebElement Utility1stParameter;
	
	
	//action method
	
	// Method to click the "campaign" button
    public void clickCampaign() {
        WebElement campaignPage = driver.findElement(click_campaign);
        campaignPage.click();
    }



	// Method to click the "launch" button
    public void launchButton() {
        WebElement launchButton = driver.findElement(click_Launch);
        launchButton.click();
    }
    
    
	
	// Method to click the "Next" button
    public void clickNext() {
        WebElement nextButton = driver.findElement(btn_next);
        nextButton.click();
    }
    
    // Method to create the "campaign" name
    public void setCampaignName(String campName) {
    	
    	// Wait for the campaign name input field to be visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement createCampaign = wait.until(ExpectedConditions.elementToBeClickable(txt_campaignName));

        // Click and send the campaign name to the input field
        createCampaign.click();
        createCampaign.clear();  // Ensure the field is cleared before entering a new name (optional, but good practice)
        createCampaign.sendKeys(campName);
        
   
    	
    }
    
    
    //click next after campaign creation
    public void clickNextAfterCampaignName() {
//        WebElement nextButton = driver.findElement(btn_campaignNameNext);
//        nextButton.click();
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            // Wait for the button to be clickable
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(btn_campaignNameNext));
            nextButton.click();
        } catch (Exception e) {
            System.out.println("Failed to click on the 'Next' button after entering the campaign name: " + e.getMessage());
        }
    }
    
    
    //method to select tag in filter section
    public void setTagName() {
    	
    	
    	    // Scroll to the dropdown element to ensure it's in view
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", select_tag);

    	    // Click the dropdown to open the search field (if necessary)
    	    try {
    	        wait.until(ExpectedConditions.elementToBeClickable(select_tag));
    	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", select_tag);
    	    } catch (ElementClickInterceptedException e) {
    	        // If click is blocked, retry with JavaScript click
    	        System.out.println("ElementClickInterceptedException encountered. Retrying with JavaScript click.");
    	    }

    	    // Locate the search input field (which is the select_tag itself in this case)
    	    WebElement searchInput = wait.until(ExpectedConditions.visibilityOf(select_tag));

    	    // Clear any pre-existing text and type the desired tag name "tags"
    	    searchInput.clear();
    	    searchInput.sendKeys("Tags");
    	    searchInput.sendKeys(Keys.ENTER);
    	    

        
    }
    
    
    //method to select tag value in filter section
	public void setTagValue() {
		
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'overlay-class')]"))); // Replace with actual overlay class

	    // Wait for the element to be clickable
	    WebElement tagValueInput = wait.until(ExpectedConditions.elementToBeClickable(select_tag_value));

	    // Scroll to the element if it is off-screen
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tagValueInput);

	    // Ensure it's visible and clickable
	    try {
	        tagValueInput.click();  // Attempt regular click
	    } catch (Exception e) {
	        // If the click is intercepted, use Actions to click
	        new Actions(driver).moveToElement(tagValueInput).click().perform();
	    }

	    // Clear the input and enter the value "aut_tag"
	    tagValueInput.clear();
	    tagValueInput.sendKeys("aut_tag");

	    // Simulate pressing Enter to confirm the input
	    tagValueInput.sendKeys(Keys.ENTER);
	
	        
	}
    
	//apply_filter
	public void clickApply() {
        WebElement applyButton = driver.findElement(apply_filter);
        applyButton.click();
    }
	
	
	// after filter click on next
	public void clickNextAfterTagName() throws InterruptedException {

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait for button to be clickable (not blocked by any overlay)
		WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//button[.//span[text()='Next']]")
		));

		// Scroll and click
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
		Thread.sleep(300);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);

		
	}
	
    
	
    // Method to select the "template" mark_test
    public void searchTemp(String query) {
    	
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    	WebElement searchInput = driver.findElement(txt_search_filter);
    	searchInput.sendKeys(query + Keys.ENTER);
    	
    	searchInput.sendKeys(Keys.ARROW_DOWN);
    	searchInput.sendKeys(Keys.ENTER);
    	
    	
   
    }
    
    
    public void clickNextAfterSelectingTemplate() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait for button to be clickable (not blocked by any overlay)
		WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//button[.//span[text()='Next']]")
		));

		// Scroll and click
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
		Thread.sleep(300);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
 	
    }
//    
    public void clickNextAfterSelectingTemplate1() throws InterruptedException {
    	

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait for button to be clickable (not blocked by any overlay)
		WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//button[.//span[text()='Next']]")
		));

		// Scroll and click
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
		Thread.sleep(300);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
    }
    
    
    //method to click on final send btn
    public void clickFinalSend() {
    	btnFinalSend.click();
    }
    
    

	public void writeIntoParameter1(String query) {
	    // Click the element to focus (if required)
	    firstParameter.click();
	
	    // Send the keys to the element
	    firstParameter.sendKeys(query);
	}
    
    
    public void writeIntoParameter2(String query) {
    	
    	// Click the element to focus (if required)
    	secondParameter.click();
	
	    // Send the keys to the element
    	secondParameter.sendKeys(query);
    	
    }
    
    //Utility1stParameter
    
		public void utilityParameter(String query) {
		    	
		    	// Click the element to focus (if required)
			Utility1stParameter.click();
			
			    // Send the keys to the element
			Utility1stParameter.sendKeys(query);
		    	
		    }


}
