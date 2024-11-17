package pageObjects_OpenCart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase_OpenCart.BasePage;

public class AccRegistrationPage extends BasePage {


	public AccRegistrationPage(WebDriver driver)
	{
		super(driver);
	}


	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFname;

	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLname;

	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelePhone; 

	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPwd;

	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPwd;

	@FindBy(xpath="//input[@name='agree']")
	WebElement checkPolicy;

	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmationMsg;

	public void enterFname(String fname)
	{
		txtFname.sendKeys(fname);
	}
	public void enterLname(String lname)
	{
		txtLname.sendKeys(lname);
	}
	public void enterEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void enterTelephoneNum(String num)
	{
		txtTelePhone.sendKeys(num);
	}
	public void enterPwd(String pwd)
	{
		txtPwd.sendKeys(pwd);
	}
	public void enterConfirmPwd(String cpwd)
	{
		txtConfirmPwd.sendKeys(cpwd);
	}
	public void SelectAgreeCb()
	{
		checkPolicy.click();
	}
	public void clickContinueBtn()
	{
		btnContinue.click();
	}
	
	public String getSuccessMsg()
	{
		try {
			return confirmationMsg.getText();
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}


}
