package day1;

import java.time.Duration;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CsvCampaign {

	WebDriver driver;
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    
	//constructor
		
	public CsvCampaign(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	



	//locator

	@FindBy(xpath = "//*[name()='path' and contains(@d,'M2.5 19h19')]")
	private WebElement click_campaign;
	
	//launch btn
	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[1]/div[3]/div/button[1]/span[1]")
	private WebElement click_Launch;
	
	
	@FindBy(xpath = "//*[@id=\"button-next-2\"]/span")
	private WebElement btn_csv_Camp;
	
	
	@FindBy(xpath = "//input[@placeholder=\"Enter campaign name\"]")
	WebElement txt_campaignName;
	
	
	@FindBy(xpath = "//*[@id=\"Next-button\"]/span[1]")
	WebElement btn_next_AfterCampaignName;
	
	
	@FindBy(xpath = "//div[contains(@class, 'MuiDropzoneArea-textContainer')]")
	WebElement upload_file;
	
	
	
	//@FindBy(xpath = "[name=\"headerInput-name\"]")
	
	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[3]/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div/input")
	WebElement map_name;
	
	
	
	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[3]/div/div[2]/div[3]/div/div/div[2]/div/div[6]/div/div/div/div/input")
	WebElement map_number;
	
	
	
	//@FindBy(xpath = "[id=\"Next-button\"]")
	
	@FindBy(xpath = "//*[@id=\"Next-button\"]/span[1]")
	WebElement btn_next;
	
			
	@FindBy(xpath = "//*[@id=\"templateAutoComplete\"]")
	WebElement search_template;
	
	
	
	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[3]/div/div[2]/div[2]/div[2]/span/button")
	WebElement btn_next_After_Temp_selection;
	
	
	
	@FindBy(xpath = "//*[@id=\"Next-button\"]/span[1]")
	WebElement btn_skip;
	
	
	
	@FindBy(xpath = "//*[@id=\"inner-grid-container\"]/div[2]/div[3]/div/div[2]/div[1]/div[4]/button/span[1]")
	WebElement btn_send;
	
	
	@FindBy(xpath = "//h5[contains(text(), \"Successfully sent Campaign\")]")
	WebElement sucess_msg;
	
	//action method/
	
			// Method to click the "campaign" button
		    public void clickCampaign() {
		    	click_campaign.click();
		    }



			// Method to click the "launch" button
		    public void launchButton() {
		    	click_Launch.click();
		    }
		    
		    
		    
		    
		    
			
			// Method to click the "Next" button
		    public void clickOnCsvCampaign() {
		    	
		    	
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    	WebElement apiCampaignButton = wait.until(ExpectedConditions.elementToBeClickable(btn_csv_Camp));
		    	apiCampaignButton.click();

		    }
		    
		    public void setApiCampaignName(String campName) {
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        WebElement createCampaign = wait.until(ExpectedConditions.elementToBeClickable(txt_campaignName));

		        // Click and send the campaign name to the input field
		        createCampaign.click();
		        createCampaign.clear();  // Ensure the field is cleared before entering a new name (optional, but good practice)
		        createCampaign.sendKeys(campName);
		    }
		    
		    public void clickOnNext() {
		    	btn_next_AfterCampaignName.click();
		    }
		    
		    
		    //method to upload csv file
		    
		    public void uploadFile() {
		    	//upload_btn.click();
		    	
		    	//driver.findElement(By.xpath("//div[contains(@class, 'MuiDropzoneArea-textContainer')]")).sendKeys("C:\\\\Users\\\\Vikas\\\\birthday_csv.csv");
		    	//Thread.sleep(2000);
		    	//upload_file.sendKeys("C:\\Users\\Vikas\\birthday_csv.csv");
		    	
//		    	WebElement uploadElement = driver.findElement(By.id("//div[contains(@class, 'MuiDropzoneArea-textContainer')]"));
//		    	uploadElement.sendKeys("C:\\Users\\Vikas\\birthday_csv.csv");
		    	
		    	WebElement fileInput = driver.findElement(By.xpath("//div//input[@accept='text/csv']"));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", fileInput);
		    	fileInput.sendKeys("C:\\Users\\Vikas\\birthday_csv.csv");
		    	
		    	
		    }
		    
		    public void mapName() {
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        WebElement createCampaign = wait.until(ExpectedConditions.elementToBeClickable(map_name));
		    	map_name.click();
		    	//map_name.sendKeys(Keys.ARROW_DOWN);
		    	map_name.sendKeys(Keys.ENTER);
		    	
		    }
		    
		    public void mapNumber() {
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        WebElement createCampaign = wait.until(ExpectedConditions.elementToBeClickable(map_number));
		        map_number.click();
		        //map_number.sendKeys(Keys.ARROW_DOWN);
		        map_number.sendKeys(Keys.ENTER);
		    	
		    }
		    
		    
		    public void clickOnNextAfterMaping() {
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        WebElement createCampaign = wait.until(ExpectedConditions.elementToBeClickable(btn_next));
		    	btn_next.click();
		    }
		    
		    public void searchTemp(String tempName) {
		    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		    	//WebElement searchInput = driver.findElement(search_template);
		    	search_template.sendKeys(tempName + Keys.ENTER);
		    	
		    	search_template.sendKeys(Keys.ARROW_DOWN);
		    	search_template.sendKeys(Keys.ENTER);
		    	
		    	 // Set up WebDriverWait
//		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		        // Scroll the element into view using JavaScript
//		        JavascriptExecutor js = (JavascriptExecutor) driver;
//		        js.executeScript("arguments[0].scrollIntoView(true);", search_template);
//
//		        // Wait until the input element is visible and interactable
//		        wait.until(ExpectedConditions.visibilityOf(search_template));
//
//		        search_template.clear();  // Clear any existing text if necessary
//		        search_template.sendKeys(tempName + Keys.ENTER);  // Type the query and press ENTER to search
//
//		        
//		        search_template.sendKeys(Keys.ARROW_DOWN);
//		        search_template.sendKeys(Keys.ENTER);  
		    }
		    
		    
		    public void clickOnNextAfterTempSelection() {
		    	
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(btn_next_After_Temp_selection));
		        
//		        System.out.println("Is displayed: " + nextButton.isDisplayed());
//		        System.out.println("Is enabled: " + nextButton.isEnabled());
		        
		        nextButton.click();
		    }
		    
		    
		    public void clickOnSkip() {
		    	btn_skip.click();
		    	
		    }
		    
		    public void clickOnSend() {
		    	btn_send.click();
		    }
		    
//		    
		    
//		    public void verifySuccessMessage() {
//		        try {
//		            // Wait for the success message to appear
//		            WebElement successMessageElement = wait.until(
//		                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(), 'Successfully sent Campaign')]"))
//		            );
//		            String actualMessage = successMessageElement.getText();
//
//		            // Define the expected message
//		            String expectedMessage = "Successfully sent Campaign";
//
//		            // Assert that the actual message matches the expected message
//		            Assert.assertEquals(actualMessage, expectedMessage, "The success message does not match!");
//
//		            System.out.println("Test Passed: Success message is displayed as expected.");
//		        } catch (AssertionError e) {
//		            System.err.println("Test Failed: " + e.getMessage());
//		        } catch (WebDriverException e) {
//		            System.err.println("Test Failed: Unable to interact with the element. " + e.getMessage());
//		        }
//		    }
		    
//		    public void verifySuccessMessage() {
//		        try {
//		            // Wait for the success message to appear
//		            WebElement successMessageElement = wait.until(
//		                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(), 'Successfully sent Campaign')]"))
//		            );
//		            String actualMessage = successMessageElement.getText();
//
//		            // Define the expected message
//		            String expectedMessage = "Successfully sent Campaign";
//
//		            // Assert that the actual message matches the expected message
//		            Assert.assertEquals("The success message does not match!", expectedMessage, actualMessage);
//
//		            System.out.println("Test Passed: Success message is displayed as expected.");
//		        } catch (AssertionError e) {
//		            System.err.println("Test Failed: " + e.getMessage());
//		        } catch (WebDriverException e) {
//		            System.err.println("Test Failed: Unable to interact with the element. " + e.getMessage());
//		        }
//		    }
		    
		    



		    
		    
		    
		    
		    
}
