package EndtoEnd;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent=ExtentReportNG.getExtentReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> tl= new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) 
	{
		test=extent.createTest(result.getMethod().getMethodName());
		tl.set(test);  //unique thread for each class
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		tl.get().log(Status.PASS, "Test is Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
	WebDriver driver = null;
	String destinationssfile = null;
	tl.get().fail(result.getThrowable());
	try {
		driver=(WebDriver) result.getTestClass().getRealClass().getField("dr").get(result.getInstance());  //will get driver 
	} 
	catch (Exception e1)
	{
		e1.printStackTrace();
	} 
	
	try {
		destinationssfile = getScreenshot(result.getMethod().getMethodName(),driver);
	} 
	catch (IOException e)
	{
		e.printStackTrace();
	}
	tl.get().addScreenCaptureFromPath(destinationssfile, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result)
	{
		
	}

	@Override
	public void onStart(ITestContext context)
	{
		
	}

	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
