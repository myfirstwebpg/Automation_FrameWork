package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver); //Sends driver to BasePage so that methods like safeClick() work
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirtname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtPhonenum;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	//@FindBy(xpath="//input[@id='input-newsletter']")
	@FindBy(xpath="//label[normalize-space()='Yes']//input[@name='newsletter']")
	WebElement chkSubscribe;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	
	//@FindBy(xpath="//button[normalize-space()='Continue']")
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirstname(String fname) {
		txtFirtname.sendKeys(fname);
	}
	
	public void setLastname(String lname) {
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPhnnum(String phnnum) {
		txtPhonenum.sendKeys(phnnum);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String confirmpwd) {
		txtConfirmPassword.sendKeys(confirmpwd);
	}
	
	
	public void setSubscribe() {
		//chkSubscribe.click(); 
		safeClick(chkSubscribe); //from base page
	}

	
	public void setPolicy() {
	    safeClick(chkPolicy);
	}

	public void clickContinue() {
	    safeClick(btnContinue);
	}
	
	//WebElement msgConfirmation;
	public String getConfirmation() {
		try {
			return(msgConfirmation.getText());
		}
		catch(Exception e) {
			return(e.getMessage());
		}
		
	}

	
}
