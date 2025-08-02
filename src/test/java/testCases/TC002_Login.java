package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_Login extends BaseClass{

	@Test (groups={"Master", "Regression"})
	public void logintest() {
		
		//Home page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
	    hp.clickLogin();
		
	    //Login page
	    LoginPage lp= new LoginPage(driver);
	    lp.setEmail(p.getProperty("email"));
	    lp.setPassword(p.getProperty("password"));
	    lp.clickLogin();
	    
	    //MyAccount page
	    MyAccountPage myact= new MyAccountPage(driver);
	    boolean targetpg= myact.isMyAccounttPgExists();
	    
	    Assert.assertEquals(targetpg, true, "Login failed");
	}
	
}
