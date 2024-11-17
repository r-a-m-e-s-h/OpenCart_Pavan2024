package utilites_OpenCart;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties properties;
	
	public ReadConfig()
	{
		try {
			FileInputStream fis = new FileInputStream(".//src//test//resources//config.properties");
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public String getUserEmail()
	{
		String uname = properties.getProperty("Email");
		return uname;
	}
	public String getPwd()
	{
		String pwd = properties.getProperty("PassWord");
		return pwd;
	}
	public String getBrowserName()
	{
		String bname = properties.getProperty("BrowserName");
		return bname;
	}
	public String getBaseURL()
	{
		String burl = properties.getProperty("BaseURL");
		return burl;
	}
	public String getEnvironment()
	{
		String env = properties.getProperty("execution_env");
		return env;
	}

}
