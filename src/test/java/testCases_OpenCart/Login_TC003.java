package testCases_OpenCart;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects_OpenCart.HomePage;
import pageObjects_OpenCart.LoginPage;
import pageObjects_OpenCart.MyAccountPage;

public class Login_TC003 extends BaseClass_OpenCart{
	
	@Test(groups= {"Smoke","Sanity"})
	public void Logintest()
	{
		logger.info("=======Starting Login_TC003=======");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccountBtn();
		hp.clickLoginBtn();
		
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(config.getUserEmail());
		lp.enterPassword(config.getPwd());
		lp.clickLoginBtn();
		
		MyAccountPage accPage = new MyAccountPage(driver);
		
		Assert.assertEquals(accPage.isMyAccountExist(), true, "Login Failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("=======Finshed Login_TC003=======");
	}
	

}
