package baseTest;

//import java.time.Duration;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseTest {
	
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass
	
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		//loading config file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		
		logger=LogManager.getLogger(this.getClass());
		
		//edge, firefox
		switch(br.toLowerCase()) {
		
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name..."); return;
		
		}
		
		//driver=new ChromeDriver();
		
		//new
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://www.app.aisensy.com/login");
		
		
		driver.get(p.getProperty("prod_Login_Url"));
		
		driver.manage().window().maximize();
	}
	
//public String captureScreenshot(String tname) {
//		
//		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		
//        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
//        File screenshotFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
//
//        
//        	
//        String targetFilePath=System.getProperty(("user.dir")+"\\screenshots\\"+ tname + "_"+timeStamp);
//        File targetFile=new File(targetFilePath);
//        
//        screenshotFile.renameTo(targetFile);
//        
//        return targetFilePath; 
//
//    } 
	public String captureScreenshot(String tname) {
	    if (driver == null) {
	        throw new IllegalStateException("WebDriver is not initialized. Cannot capture screenshot.");
	    }

	    try {
	        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);

	        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	        File target = new File(targetFilePath);

	        FileUtils.copyFile(source, target); // Apache Commons IO
	        return targetFilePath;

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to capture screenshot.", e);
	    }
	}

	
	@AfterClass
	public void tearDown() {
		driver.quit();
		
	}

}
