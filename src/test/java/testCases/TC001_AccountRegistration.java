package testCases;

import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

import org.testng.Assert;

public class TC001_AccountRegistration extends BaseClass{
  
	   //public WebDriver driver; // ← shadowing the parent one
	// no need to declare driver again
	/*declaring a new, separate driver variable that shadows the one from BaseClass. 
	 * This causes:
	❌ Confusion: You may accidentally use the child’s driver which is null, instead of the one initialized in BaseClass.
*/
 

  @Test (groups={"Master", "Sanity"})
  public void verify_account_registration() {
	  
	  HomePage hp = new HomePage(driver);
	  hp.clickMyAccount();
	  hp.clickRegister();
	  
	  AccountRegistrationPage regpg = new AccountRegistrationPage(driver);
	  regpg.setFirstname(randomString().toLowerCase());
	  regpg.setLastname(randomString().toLowerCase());
	  regpg.setEmail(randomAlphaNumeric()+"@gmail.com");
	  regpg.setPhnnum(randomNumber());
	  
	  String password = randomString();
	  
	  regpg.setPassword(password);
	  regpg.setConfirmPassword(password);
	  regpg.setSubscribe();
	  regpg.setPolicy();
	  regpg.clickContinue();
	  String confirmmsg= regpg.getConfirmation();
	  
	  Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
  }
  
  

}
