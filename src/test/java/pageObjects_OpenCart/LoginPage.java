package pageObjects_OpenCart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase_OpenCart.BasePage;

public class LoginPage extends BasePage{


	public LoginPage(WebDriver driver)
	{
		super(driver);

	}


	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPwd;

	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;

	public void enterEmail(String email) 
	{
		txtEmail.sendKeys(email);
	}
	public void enterPassword(String pwd)
	{
		txtPwd.sendKeys(pwd);
	}
	public void clickLoginBtn()
	{
		btnLogin.click();
	}

}
