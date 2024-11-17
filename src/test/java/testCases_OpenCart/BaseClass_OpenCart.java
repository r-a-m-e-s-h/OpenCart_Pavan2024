package testCases_OpenCart;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilites_OpenCart.ReadConfig;

import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger;     //log4j

public class BaseClass_OpenCart {

	public static WebDriver driver;

	public ReadConfig config = new ReadConfig();

	public Logger logger;

	@BeforeClass(groups= {"Sanity","Smoke","DataDriven","Regression"})
	@Parameters({"OS","Browser"})
	public void setUp(String os, String browser) throws MalformedURLException
	{
		//log4j2
		logger = LogManager.getLogger(this.getClass());

		//remote execution
		if(config.getEnvironment().equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilites = new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilites.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilites.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilites.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No Matching OS");
				return;
			}
			//browser
			if(browser.equalsIgnoreCase("chrome"))
			{
				capabilites.setBrowserName("chrome");
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				capabilites.setBrowserName("edge");
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				capabilites.setBrowserName("firefox");
			}
			else
			{
				System.out.println("No Matching Browser");
				return;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), capabilites);	

		}

		//local execution
		if(config.getEnvironment().equalsIgnoreCase("local"))
		{
			switch(browser.toLowerCase())
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			default : System.out.println("Invalid browser ..."); return;
			}
		}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(config.getBaseURL());
			logger.info("browser Opened");

		
	}
	@AfterClass(groups= {"Sanity","Smoke","DataDriven","Regression"})
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
			logger.info("browser closed");

		}
	}
	public String TakeScreenShot(String tcName)
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhss").format(new Date());

		TakesScreenshot ss = (TakesScreenshot)driver;
		File source = ss.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir")+".//screenshots"+ tcName +"_"+timeStamp+".png";
		File dest = new File(targetFilePath);

		source.renameTo(dest);

		return targetFilePath; // return name of the file
	}
	public String randomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}
	public String randomNumber()
	{
		return RandomStringUtils.randomNumeric(10);
	}
	public String ApphaNumeric()
	{
		String randomName = RandomStringUtils.randomAlphabetic(5);
		String randomNumber = RandomStringUtils.randomNumeric(4);
		return (randomName+"@"+randomNumber);
	}

}
