package pageObjects_OpenCart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase_OpenCart.BasePage;

public class HomePage extends BasePage {


	public HomePage(WebDriver driver)
	{
		super(driver);
	}


	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement myAccountBtn;

	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement regBtn;

	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement btnLogin;
	
	public void clickMyAccountBtn()
	{
		myAccountBtn.click();
	}
	public void clickRegBtn()
	{
		regBtn.click();
		//return new AccRegistrationPage(driver);
	}
	public void clickLoginBtn()
	{
		btnLogin.click(); 
		//return new LoginPage(driver);
	}

}
