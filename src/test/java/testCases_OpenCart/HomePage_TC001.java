package testCases_OpenCart;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects_OpenCart.HomePage;

public class HomePage_TC001 extends BaseClass_OpenCart{
	
	
	@Test(groups= {"Smoke","Sanity"})
	public void homePageTest()
	{
		logger.info("=======Starting HomePage_TC001=======");
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccountBtn();
			hp.clickRegBtn();
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("=======Finsihed HomePage_TC001=======");
		
	}

}
