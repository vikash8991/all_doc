package aiSensyTest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener {
	
	//only ones 
	public void onStart(ITestContext context) {
	    // not implemented
		System.out.println("on start only one time");
	  }
	
	//Before every method it will execute
	public void onTestStart(ITestResult result) {
	    // not implemented
		System.out.println("on test started for every method");
	  }

	
	public void onTestSuccess(ITestResult result) {
	    // not implemented
		System.out.println("passed test");
	  }
	
	public void onTestFailure(ITestResult result) {
	    // not implemented
		System.out.println("test failed");
	  }
	
	public void onTestSkipped(ITestResult result) {
	    // not implemented
		System.out.println("passed skiped");
	  }
	
	
	public void onFinish(ITestContext context) {
	    // not implemented
		System.out.println(" test finished");
	  }
	
	
	
	
	
}
