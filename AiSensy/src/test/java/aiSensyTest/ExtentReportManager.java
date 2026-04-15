package aiSensyTest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener{
    public static void main(String[] args) {
        // Create an ExtentSparkReporter instance
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("sparkReport.html");

        // Configure the reporter (optional)
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setDocumentTitle("Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        // Create an ExtentReports instance
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Start logging test cases
        extent.createTest("Sample Test Case")
              .pass("Test case passed");

        extent.createTest("Another Test Case")
              .fail("Test case failed");
        
        //Configure Spark Reporter (Optional)
        sparkReporter.config().setTheme(Theme.STANDARD); // or DARK
        sparkReporter.config().setReportName("Custom Report Name");
        sparkReporter.config().setDocumentTitle("Custom Document Title");
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        // Flush the report
        extent.flush();

        System.out.println("Report Generated: sparkReport.html");
    }
}
