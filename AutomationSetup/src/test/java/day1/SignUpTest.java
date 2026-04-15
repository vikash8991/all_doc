package day1;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(day1.ExtentReportManager.class) 
public class SignUpTest {

    WebDriver driver;

    private String generateUniqueEmail() {
        String uniqueID = UUID.randomUUID().toString().substring(0, 8);
        return "user_" + uniqueID + "@mailinator.com";
    }

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.app.aisensy.com/signup");
        driver.manage().window().maximize();
    }

    @Test
    void SignUp() {
        ExtentTestManager.getTest().info("Starting SignUp Test");

        String uniqueEmail = generateUniqueEmail();
        SignUp sp = new SignUp(driver);

        ExtentTestManager.getTest().info("Filling signup form");
        sp.setFullName("intvikash");
        sp.setEmail(uniqueEmail);
        sp.setContact("8279396355");
        sp.setPass("12345@A12");
        sp.setConfPass("12345@A12");
        sp.clickFreeTrail();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(sp.txt_companyName));

        ExtentTestManager.getTest().info("Filling company details");
        sp.setCompanyName("Aisensy");
        sp.setcompanyDesc("descfjygukh");
        sp.selectState("Bihar");
        sp.selectSize("1 - 10 Employees");
        sp.selectIndustries("IT Services");
        sp.clickSubmit();

        // ✅ Assertion & Logging to Extent Report
        ExtentTestManager.getTest().info("Waiting for dashboard to load...");
        wait.until(ExpectedConditions.urlContains("dashboard"));

        String currentUrl = driver.getCurrentUrl();
        try {
            Assert.assertTrue(currentUrl.contains("dashboard"), "User is not redirected to dashboard after signup!");
            ExtentTestManager.getTest().pass("Signup completed successfully and redirected to dashboard");
        } catch (AssertionError e) {
            ExtentTestManager.getTest().fail("Signup failed: " + e.getMessage());
            throw e; // Re-throw to let TestNG mark it as failed
        }
    }
}
