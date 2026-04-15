//not using right now

//package utilities;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//public class ExtentReportManager implements ITestListener {
//
//    // Declare ExtentReports and ExtentTest as static for global use
//    private static ExtentReports extent;
//    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // Thread-safe test logging
//
//    // Initialize the ExtentReports object
//    private static ExtentReports createInstance() {
//        // Define the path for the report
//        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
//
//        // Configure the reporter
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
//        sparkReporter.config().setDocumentTitle("Automation Test Report");
//        sparkReporter.config().setReportName("Test Execution Results");
//        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
//
//        // Initialize ExtentReports and attach the reporter
//        ExtentReports extentReports = new ExtentReports();
//        extentReports.attachReporter(sparkReporter);
//
//        // Add system info
//        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
//        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
//        extentReports.setSystemInfo("Tester", "Your Name");
//        return extentReports;
//    }
//
//    // Provide a global access point for the ExtentReports object
//    public static synchronized ExtentReports getExtentReports() {
//        if (extent == null) {
//            extent = createInstance();
//        }
//        return extent;
//    }
//
//    @Override
//    public void onStart(ITestContext context) {
//        // Initialize ExtentReports when the suite starts
//        extent = getExtentReports();
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        // Flush the ExtentReports when the suite finishes
//        if (extent != null) {
//            extent.flush();
//        }
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        // Create a new test entry in the report
//        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
//        extentTest.set(test); // Assign the test to the current thread
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        // Log the test result as PASSED
//        extentTest.get().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        // Log the test result as FAILED with exception details
//        extentTest.get().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
//        extentTest.get().log(Status.FAIL, result.getThrowable());
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        // Log the test result as SKIPPED
//        extentTest.get().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        // Optional: Handle partial success scenarios
//    }
//
//    @Override
//    public void onTestFailedWithTimeout(ITestResult result) {
//        // Log timeout failures
//        extentTest.get().log(Status.FAIL, "Test failed due to timeout: " + result.getMethod().getMethodName());
//    }
//}
