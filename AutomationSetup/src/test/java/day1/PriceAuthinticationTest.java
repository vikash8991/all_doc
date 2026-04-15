package day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PriceAuthinticationTest {

    WebDriver driver;
    price lp;
    String priceInitial;
    String priceAfter;

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.app.aisensy.com/login");
        driver.manage().window().maximize();
        lp = new price(driver);
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
        System.out.println("Captured Price Initialy: " + priceInitial);
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

//    @Test(priority = 5)
//    void CampaignFromPriceMarketing() throws InterruptedException{
//
//    	lp.campCreationMarketing();
//    }
    @Test(priority = 5)
    void campCreationAuthintecationTest() throws InterruptedException {
    	lp.campCreationAuthintecation();
    }
    
    @Test(priority = 7)
    void priceAfterCamp() throws InterruptedException {
    	
    	Thread.sleep(30000);
    	// 🔄 Refresh the browser
        driver.navigate().refresh();
        
        priceAfter = lp.getPrice();
        System.out.println("Captured Price After camp: " + priceAfter);

    }
    
    
    @Test(priority = 6)
    void clickOnDashBoard() {
    	lp.clickedOnDash();
    }
    
//    void marketingCharges() {
//    	String result= priceInitial - priceAfter;
//    }
    @Test(priority = 8)
    void marketingCharges() throws InterruptedException {
    	
        driver.navigate().refresh();
        
    	Thread.sleep(10000);
        try {
            double initial = Double.parseDouble(priceInitial.replaceAll("[^\\d.]", ""));
            double after = Double.parseDouble(priceAfter.replaceAll("[^\\d.]", ""));
            double result = initial - after;

            // Option 1 (simple): Format to 2 decimal places
            System.out.println("AiSensy Authintication Charges: ₹ " + String.format("%.3f", result));

            // Option 2 (uncomment if you prefer DecimalFormat)
            // DecimalFormat df = new DecimalFormat("#.00");
            // System.out.println("Marketing Charges: ₹ " + df.format(result));

        } catch (NumberFormatException e) {
            System.out.println("Error parsing price strings: " + e.getMessage());
        }
    }


}
