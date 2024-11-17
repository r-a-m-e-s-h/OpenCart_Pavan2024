package testCases_OpenCart;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects_OpenCart.HomePage;
import pageObjects_OpenCart.LoginPage;
import pageObjects_OpenCart.MyAccountPage;
import utilites_OpenCart.DataProviders_OpenCart;


/*

LoginData from excel Valid
	1- Login success -test pass - logout
	2- login failed -test fail

LoginData from excel InValid
	1- Login success -test fail - logout
	2-login failed - test pass

*/

public class DDLogin_TC_004 extends BaseClass_OpenCart{
	
	
	@Test(dataProviderClass = DataProviders_OpenCart.class ,dataProvider = "LoginData1",groups= {"DataDriven"})
	public void testDDLogin(String email, String pwd, String expResult)
	{
		logger.info("======Starting DDLogin_TC_004========");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccountBtn();
		hp.clickLoginBtn();
		
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(pwd);
		lp.clickLoginBtn();
		
		
		MyAccountPage accPage = new MyAccountPage(driver);
		boolean status = accPage.isMyAccountExist();
		
		if(expResult.equalsIgnoreCase("Valid"))
		{
			if(status==true)
			{
				accPage.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(expResult.equalsIgnoreCase("Invalid"))
		{
			if(status==true)
			{
				accPage.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			Assert.fail();
		}
		logger.info("======Finished DDLogin_TC_004========");
		
	}

}
