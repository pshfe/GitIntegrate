package EndtoEnd;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG 

{
	static ExtentReports er;
	public static ExtentReports getExtentReport()
	{
		String path =System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter esr= new ExtentSparkReporter(path);
		esr.config().setReportName("Automation Results");
		esr.config().setDocumentTitle("Web Automation Reports");
		er= new ExtentReports();
		er.attachReporter(esr);
		er.setSystemInfo("Tester", "Pooja Shetty");
		return er;
	}
}
