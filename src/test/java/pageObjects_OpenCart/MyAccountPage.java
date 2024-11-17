package pageObjects_OpenCart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase_OpenCart.BasePage;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//div[@class='list-group']//a[normalize-space()='Logout']")
	WebElement lnkLogout;
	
	
	public boolean isMyAccountExist()
	{
		try {
			return msgHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
	
	

}
