package utilites_OpenCart;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.opentelemetry.semconv.UrlAttributes;
import testCases_OpenCart.BaseClass_OpenCart;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;

	public ExtentReports extent;

	public ExtentTest test;

	String reportName;

	public  void onStart(ITestContext context)
	{
		/*
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		 */

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-"+timeStamp+".html";

		sparkReporter = new ExtentSparkReporter(".//reports//"+reportName);

		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("System Name", "Local Host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));


		String os = context.getCurrentXmlTest().getParameter("OS");
		extent.setSystemInfo("OS", os);

		String browser = context.getCurrentXmlTest().getParameter("Browser");
		extent.setSystemInfo("BROWSER", browser);

		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includeGroups.toString());
		}

	}
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display group name in report
		test.log(Status.PASS,result.getName()+" Executed Successfully. ");
	}
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display group name in report
		
		test.log(Status.FAIL,result.getName()+" Failed. ");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
		String imgpath = new BaseClass_OpenCart().TakeScreenShot(result.getName());
		test.addScreenCaptureFromPath(imgpath);
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display group name in report
		test.log(Status.SKIP,result.getName()+" Skipped.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context)
	{
		extent.flush();
		
		//open automatically
		String pathOfExtentReport = System.getProperty("user.dir")+".//reports//"+reportName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		//send email after execution 
		try {
			URL myUrl = new URL("file:///"+System.getProperty("user.dir")+"\\reports_OpenCart\\"+reportName);
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(myUrl));
			email.setHostName("smtp.google.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("mrameshbabu2104@gmail.com", "password") );
			email.setSSLOnConnect(true);
			email.setFrom("mrameshbabu2104@gmail.com");
			email.setSubject("OpenCart Test Results");
			email.setMsg("please find Attached Report");
			email.addTo("rameshbabu.manikkam@gmail.com");
			email.attach(myUrl,"extent report","please check report....");
			email.send();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		*/
		
	}

}
