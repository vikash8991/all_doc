package day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class StaticDrop {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		
		//driver.get("https://www.app.aisensy.com/signup");
		
		//staging
			driver.get("https://staging.dj8sl4xl9bxki.amplifyapp.com/signup");
		


		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("vikash");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("vikashfre898lan98teskjyht9987y871@gmail.com");
		driver.findElement(By.xpath("//input[@id='contact']")).sendKeys("8279396355");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("vikashfreeplantest1@gmail.com");
		
		//pass
		driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("vikashfreeplantest1@gmail.com");
		driver.findElement(By.xpath("//span[normalize-space()='Start Your FREE Trial']")).click();
		
//		Thread.sleep(20000);
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("company")));
        
        driver.findElement(By.id("company")).sendKeys("qa");
        driver.findElement(By.id("companyDescription")).sendKeys("qa desc");

        // Select state (open dropdown, then click desired option)
        WebElement stateDropdown = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[3]/div/div[6]/div/div/input"));
        stateDropdown.click();  // Open dropdown
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Bihar']")));
        driver.findElement(By.xpath("//li[normalize-space()='Bihar']")).click();  // Select "Bihar" option
       
 
		
        WebElement sizeDropdown = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[3]/div/div[7]/div/div/input"));
        sizeDropdown.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='1 - 10 Employees']")));
        driver.findElement(By.xpath("//li[normalize-space()='1 - 10 Employees']")).click(); 
        

		
		WebElement industriesDropdown = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[3]/div/div[8]/div/div/input"));
		industriesDropdown.click();
	        
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='IT Services']")));
	    driver.findElement(By.xpath("//li[normalize-space()='IT Services']")).click(); 
		
	    Thread.sleep(5000);
		driver.findElement(By.xpath("//span[normalize-space()='Complete Sign Up']")).click();
		
		Thread.sleep(5000);

	}

}
