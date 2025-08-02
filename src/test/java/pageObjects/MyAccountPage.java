package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//@FindBy(xpath="//h1[normalize-space()='My Account']")
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	//a[@class='list-group-item'][normalize-space()='Logout']
	WebElement lnkLogout;
	
	public boolean isMyAccounttPgExists() {
		try {
			return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout() {
		safeClick(lnkLogout);
	}
}
