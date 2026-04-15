package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseTest.BaseTest;

public class Listeners extends BaseTest implements ITestListener {


	private ExtentReports extent;
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        // Initialize the ExtentReports instance when the test suite starts
        extent = ExtentReporterNG.getReportObject();
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the ExtentReports when the test suite finishes
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test entry in the report
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log the test result as PASSED
        extentTest.get().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
    }


    @Override
    public void onTestFailure(ITestResult result) {
        // Log the test result as FAILED with exception details
        extentTest.get().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.FAIL, result.getThrowable());

        // Capture screenshot and add it to the report
        
        try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception el) {
			el.printStackTrace();
		}
        
        String filepath = captureScreenshot(result.getMethod().getMethodName());

        extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        // Log the test result as SKIPPED
        extentTest.get().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional: Handle partial success scenarios
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Log timeout failures
        extentTest.get().log(Status.FAIL, "Test failed due to timeout: " + result.getMethod().getMethodName());
    }
}
