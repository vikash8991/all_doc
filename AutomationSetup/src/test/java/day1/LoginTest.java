package day1;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Listeners;

@Listeners(day1.ExtentReportManager.class)
public class LoginTest {
	
	//Without page factory
	
	WebDriver driver;
	
	// setup
	@BeforeClass
	void setup() {
		driver=new ChromeDriver();
		driver.get("https://www.app.aisensy.com/login");
		driver.manage().window().maximize();
	}
	
	//test method
	
	@Test
	void login() {
	    ExtentTest test = ExtentTestManager.getTest();

	    LoginPage lp = new LoginPage(driver);

	    test.info("Entering username");
	    lp.setUsername("testjulyprod@mailinator.com");

	    test.info("Entering password");
	    lp.setPassword("12345@A12a");

	    test.info("Clicking Login button");
	    lp.clickLogin();

	    // Simple wait: wait up to 15 seconds for URL to change from login page
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://www.app.aisensy.com/login")));

	    // Now check URL
	    String actualUrl = driver.getCurrentUrl().toLowerCase();
	    test.info("Current URL after login: " + actualUrl);

	    boolean loginSuccess = actualUrl.contains("/dashboard") || actualUrl.contains("/projects");

	    if (loginSuccess) {
	        test.pass("Login successful. Redirected to dashboard or projects page.");
	    } else {
	        test.fail("Login failed or unexpected redirection. Actual URL: " + actualUrl);
	    }

	    Assert.assertTrue(
	        loginSuccess,
	        "Login failed or unexpected redirection. Actual URL: " + actualUrl
	    );
	
	}



}
