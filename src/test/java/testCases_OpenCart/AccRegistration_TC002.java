package testCases_OpenCart;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;
import pageObjects_OpenCart.AccRegistrationPage;
import pageObjects_OpenCart.HomePage;

public class AccRegistration_TC002  extends BaseClass_OpenCart{

	
	@Test(groups= {"Smoke","Sanity"})
	public void AccRegtest()
	{
		logger.info("=======Starting AccRegistration_TC002=======");
		try 
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccountBtn();
		hp.clickRegBtn();

		AccRegistrationPage regPage = new AccRegistrationPage(driver);
		regPage.enterFname("Tommy");
		regPage.enterLname("Jerry");
		regPage.enterEmail(randomString()+"@test.com");
		regPage.enterTelephoneNum(randomNumber());

		String password = ApphaNumeric();

		regPage.enterPwd(password);
		regPage.enterConfirmPwd(password);

		regPage.SelectAgreeCb();
		regPage.clickContinueBtn();


		Assert.assertEquals(regPage.getSuccessMsg(), "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("=======Finished AccRegistration_TC002=======");


	}




}
