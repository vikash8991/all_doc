package day1;

import day1.ExtentTestManager;
import utils.MongoDBUtils;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(day1.ExtentReportManager.class)

public class PriceMarketingTest {

    WebDriver driver;
    price lp;
    String priceInitial;
    String priceAfter;
    
    //added for mongo
    MongoDBUtils mongo;
    double dbCreditBefore;
    double dbCreditAfter;
    String userId = "67860cbac4ec1f0bf99941fd"; // Replace with correct ID

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.app.aisensy.com/login");
        driver.manage().window().maximize();
        lp = new price(driver);
        
     // Init Mongo
        mongo = new MongoDBUtils();
    }
    
    
    @Test(priority = 0)
    void captureDbPriceBefore() {
        Object creditObj = mongo.getTemplateCreditById(userId);
        if (creditObj != null) {
            dbCreditBefore = Double.parseDouble(creditObj.toString());
            String log = "DB Template Credit Before Campaign: ₹ " + dbCreditBefore;
            System.out.println(log);
            ExtentTestManager.getTest().info(log);
        } else {
            ExtentTestManager.getTest().fail("Could not fetch templateCredit from DB before campaign.");
        }
    }
    

    @Test(priority = 1)
    void login() throws InterruptedException {
        lp.setUsername("testjulyprod@mailinator.com");
        lp.setPassword("12345@A12");
        lp.clickLogin();
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    void price() {
        priceInitial = lp.getPrice();
        String log = "Captured Price Initially: " + priceInitial;
        System.out.println(log);
        ExtentTestManager.getTest().info(log);
    }

    @Test(priority = 2)
    void selectProject() throws InterruptedException {

        lp.selectproject();

        Thread.sleep(2000);
    }
    
    @Test(priority=3)
    void closePopup() {
    	// Close popups before interacting with project view
        lp.close2fabtn();
        lp.closeAi();
        lp.closeFB();
    }

    @Test(priority = 5)
    void CampaignFromPriceMarketing() throws InterruptedException{
    	lp.campCreationMarketing();
    }
  
    
    @Test(priority = 7)
    void priceAfterCamp() throws InterruptedException {
        Thread.sleep(30000);
        driver.navigate().refresh();

        priceAfter = lp.getPrice();
        String log = "Captured Price After camp: " + priceAfter;
        System.out.println(log);
        ExtentTestManager.getTest().info(log);
    }
    
    
    @Test(priority = 6)
    void clickOnDashBoard() {
    	lp.clickedOnDash();
    }
    
    
    @Test(priority = 8)
    void marketingCharges() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(10000);

        try {
            double initial = Double.parseDouble(priceInitial.replaceAll("[^\\d.]", ""));
            double after = Double.parseDouble(priceAfter.replaceAll("[^\\d.]", ""));
            double result = initial - after;

            String log = "AiSensy Marketing Charges: ₹ " + String.format("%.3f", result);
            System.out.println(log);
            ExtentTestManager.getTest().info(log);

        } catch (NumberFormatException e) {
            ExtentTestManager.getTest().fail("Price parsing error: " + e.getMessage());
        }
    }
    
    @Test(priority = 9)
    void captureDbPriceAfter() {
        Object creditObj = mongo.getTemplateCreditById(userId);
        if (creditObj != null) {
            dbCreditAfter = Double.parseDouble(creditObj.toString());
            String log = "DB Template Credit After Campaign: ₹ " + dbCreditAfter;
            System.out.println(log);
            ExtentTestManager.getTest().info(log);

            double diff = dbCreditBefore - dbCreditAfter;
            ExtentTestManager.getTest().info("DB Deducted Template Credit: ₹ " + String.format("%.3f", diff));

        } else {
            ExtentTestManager.getTest().fail("Could not fetch templateCredit from DB after campaign.");
        }
    }

    
    @AfterClass
    void tearDown() {
        if (mongo != null) mongo.close();
        if (driver != null) driver.quit();
    }

    //12 ya 13   --->0.120 ya 0.130
    //88 ya 89   ---> 0.880 ya 0.890
}
