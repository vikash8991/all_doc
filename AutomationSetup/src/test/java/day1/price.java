package day1;

import java.time.Duration;
import java.util.UUID;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class price {

    WebDriver driver;

    // Constructor
    public price(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By txt_username = By.xpath("//input[@id='email']");
    By txt_pass = By.xpath("//input[@id='password']");
    By btn_login = By.xpath("//h5[text()='Continue']");

    By chooseProject = By.xpath("(//span[contains(text(),'View')])[1]");
    By price_xpath = By.xpath("(//h3[contains(@class, 'MuiTypography-h3') and contains(., '₹')])[1]");
    By close2fa = By.xpath("//button[@data-testid='close-Enable2FA']");
    By closeAiTemp = By.xpath("//button[@data-testid='close-Info-AI']");
    By closeFB = By.xpath("//button[@data-testid='close-Feature-base']");
    
    By dashboardElement = By.xpath("//div[@id='dashboard']");
    
    
    

    // Actions
    public void setUsername(String user) {
        driver.findElement(txt_username).sendKeys(user);
    }

    public void setPassword(String pass) {
        driver.findElement(txt_pass).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(btn_login).click();
    }

    public void selectproject() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            WebElement projectBtn = wait.until(ExpectedConditions.elementToBeClickable(chooseProject));
            projectBtn.click();
            System.out.println("Project selection is working properly");
        } catch (TimeoutException e) {
            System.out.println("Project selection button not found!");
        }
    }

    public void close2fabtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(close2fa));
            try {
                closeBtn.click();
            } catch (Exception e) {
                System.out.println("Normal click failed. Trying JavaScript click for 2FA close.");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
            }
            wait.until(ExpectedConditions.invisibilityOfElementLocated(close2fa));
        } catch (TimeoutException e) {
            System.out.println("2FA popup not found or already closed.");
        }
    }

    public void closeAi() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement aiCloseBtn = wait.until(ExpectedConditions.elementToBeClickable(closeAiTemp));
            aiCloseBtn.click();
            wait.until(ExpectedConditions.invisibilityOf(aiCloseBtn));
        } catch (TimeoutException e) {
            System.out.println("AI popup not found or already closed.");
        }
    }
    
    public void closeFB() {
    	driver.findElement(closeFB).click();
    }

    public String getPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(price_xpath));
        return priceElement.getText(); // returns like "₹ 46.16"
    }

    void campCreationMarketing() throws InterruptedException {
		Camp ct=new Camp(driver);
		
        String uniqueCampaignName = "Campaign_" + UUID.randomUUID().toString().substring(0, 6);

		
		Thread.sleep(2000);
		ct.clickCampaign();
		
		Thread.sleep(2000);
		ct.launchButton();
		
		Thread.sleep(2000);
		ct.clickNext();
		
		Thread.sleep(2000);
		ct.setCampaignName(uniqueCampaignName);
		
		Thread.sleep(2000);
		ct.clickNextAfterCampaignName();
		
		Thread.sleep(2000);
		ct.setTagName();
		
		
		Thread.sleep(2000);
		ct.setTagValue();
		
		Thread.sleep(2000);
		ct.clickApply();
		
		Thread.sleep(2000);
		ct.clickNextAfterTagName();
		
		Thread.sleep(2000);
		ct.searchTemp("test345");
		
		
		Thread.sleep(2000);
		ct.clickNextAfterSelectingTemplate();
		
		Thread.sleep(2000);
		ct.clickNextAfterSelectingTemplate1();
		
		Thread.sleep(2000);
		ct.clickFinalSend();
		
		
		
	}
    
    
    void campCreationAuthintecation() throws InterruptedException {
		Camp ct=new Camp(driver);
		
//		String uniqueCampaign=generateUniqueCampaignName();
        String uniqueCampaignName = "Campaign_" + UUID.randomUUID().toString().substring(0, 6);

		
		Thread.sleep(2000);
		ct.clickCampaign();
		
		Thread.sleep(2000);
		ct.launchButton();
		
		Thread.sleep(2000);
		ct.clickNext();
		
		Thread.sleep(2000);
		ct.setCampaignName(uniqueCampaignName);
		
		Thread.sleep(2000);
		ct.clickNextAfterCampaignName();
		
		Thread.sleep(2000);
		ct.setTagName();
		
		
		Thread.sleep(2000);
		ct.setTagValue();
		
		Thread.sleep(2000);
		ct.clickApply();
		
		Thread.sleep(2000);
		ct.clickNextAfterTagName();
		
		Thread.sleep(2000);
		ct.searchTemp("auth_14july");
		
		//driver.findElement(By.xpath("(//div[@placeholder='Type or select an attribute'])[1]"));
		//driver.findElement(By.xpath("(//div[@placeholder='Type or select an attribute'])[2]"));
		Thread.sleep(2000);
		ct.writeIntoParameter1("1233");
		ct.writeIntoParameter2("2333");
		
		Thread.sleep(2000);
		ct.clickNextAfterSelectingTemplate();
		
		Thread.sleep(2000);
		ct.clickNextAfterSelectingTemplate1();
		
		Thread.sleep(2000);
		ct.clickFinalSend();
		
		
		
	}
    
    void campCreationUtility() throws InterruptedException {
		Camp ct=new Camp(driver);
		
//		String uniqueCampaign=generateUniqueCampaignName();
        String uniqueCampaignName = "Campaign_" + UUID.randomUUID().toString().substring(0, 6);

		
		Thread.sleep(2000);
		ct.clickCampaign();
		
		Thread.sleep(2000);
		ct.launchButton();
		
		Thread.sleep(2000);
		ct.clickNext();
		
		Thread.sleep(2000);
		ct.setCampaignName(uniqueCampaignName);
		
		Thread.sleep(2000);
		ct.clickNextAfterCampaignName();
		
		Thread.sleep(2000);
		ct.setTagName();
		
		
		Thread.sleep(2000);
		ct.setTagValue();
		
		Thread.sleep(2000);
		ct.clickApply();
		
		Thread.sleep(2000);
		ct.clickNextAfterTagName();
		
		Thread.sleep(2000);
		ct.searchTemp("test_ut14july");
		
		//driver.findElement(By.xpath("(//div[@placeholder='Type or select an attribute'])[1]"));
		//driver.findElement(By.xpath("(//div[@placeholder='Type or select an attribute'])[2]"));
//		Thread.sleep(2000);
		ct.utilityParameter("1233");
//		ct.writeIntoParameter2("2333");
		
		Thread.sleep(2000);
		ct.clickNextAfterSelectingTemplate();
		
		Thread.sleep(2000);
		ct.clickNextAfterSelectingTemplate1();
		
		Thread.sleep(2000);
		ct.clickFinalSend();
		
		
		
	}



    void clickedOnDash() {
    	driver.findElement(dashboardElement).click();
    	
    }

}
