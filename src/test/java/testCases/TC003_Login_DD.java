package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_Login_DD extends BaseClass{

	@Test(groups={"Data-driven"}, dataProvider = "LoginData", dataProviderClass= DataProviders.class) //getting data provider from diff class
	public void logintest(String email, String pwd, String expectedResult) {
		
		//Home page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
	    hp.clickLogin();
		
	    //Login page
	    LoginPage lp= new LoginPage(driver);
	    lp.setEmail(email);
	    lp.setPassword(pwd);
	    lp.clickLogin();
	    
	    //MyAccount page
	    MyAccountPage myact= new MyAccountPage(driver);
	    boolean targetpg= myact.isMyAccounttPgExists();
	    
	    /*
	     * Test Logic:
	     * --------------------------------------------
	     * Data is valid - login success - test pass - logout
	     *              - login failed - test fail
	     * Data is invalid - login success - test fail - logout
	     *                - login failed - test pass
	     */
	   
	   // Test logic based on expected result
	    if(expectedResult.equalsIgnoreCase("Valid"))
	    {
	    	if(targetpg)
	    	{
		        myact.clickLogout();
		        Assert.assertTrue(targetpg, "Login successful as expected.");
	    	}
	    	else
	    	{
	    		//Assert.assertFalse(targetpg, "Login failed, but was expected to succeed.");
	    		Assert.fail("Login failed, but was expected to succeed.");
	    	}
	    }
	    
	    else if(expectedResult.equalsIgnoreCase("Invalid"))
	    {
	    	if(targetpg)
	    	{
		        myact.clickLogout();
		        //Assert.assertFalse(targetpg, "Login succeeded, but was expected to fail.");
		        Assert.fail("Login succeeded, but was expected to fail.");
	    	}
	    	else
	    	{
	    		//Assert.assertTrue(targetpg, "Login failed as expected");
	    		Assert.assertFalse(targetpg, "Login failed as expected");
	    	}
	    }
	}
	
}
/*Assert.assertTrue/False(...) → Use only when you're checking an expected boolean

Assert.fail(...) → Use when you're saying “this should not have happened”
(like login failed when it was expected to succeed)


| Condition            | When to Use                    |
| -------------------- | ------------------------------ |
| Expect login to pass | `Assert.assertTrue(targetpg)`  |
| Expect login to fail | `Assert.assertFalse(targetpg)` |
| Something unexpected | `Assert.fail("...")`           |

*/