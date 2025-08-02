package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	
	   public static WebDriver driver;
	   public Properties p;
	   
	//Without parameterization
/*	@BeforeClass (groups= {"Base"})
	public void setUp() throws InterruptedException, IOException {	
		
		//Loading config.properties file
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		p = new Properties();			
		p.load(file);
		
		driver = new ChromeDriver(); 
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://demo.opencart.com/");
		driver.get(p.getProperty("appURL2"));
		driver.manage().window().maximize();
	   }
	 */

	//With parameterization- can only run through xml file
	  @Parameters({"browser","os"})
	  //@Parameters("browser")
	  @BeforeClass (groups= {"Base"})
	  public void setUp(String br, String os) throws InterruptedException, IOException {
		
		//Loading config.properties file
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		switch (br.toLowerCase()) {
		case "chrome": driver = new ChromeDriver(); break;
		case "edge": driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default: System.out.println("Invalid browser..."); return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://demo.opencart.com/");
		driver.get(p.getProperty("appURL2"));
		driver.manage().window().maximize();
	  } 
	  
	  //Using selenium grid
/*	  @SuppressWarnings("deprecation")
	@Parameters({"browser","os"})
	  @BeforeClass (groups= {"Base"})
	  public void setUp(String br, String os) throws InterruptedException, IOException {
		
		//Loading config.properties file
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
		
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform (Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform (Platform.MAC);
			}
			else if (os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform (Platform.LINUX);
			}
			else 
			{				
				System.out.println("No matching os");
				return;
			}

			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("Microsoft Edge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver = new ChromeDriver(); break; 
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default: System.out.println("Invalid browser name.."); return;
			}
		}

		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://demo.opencart.com/");
		driver.get(p.getProperty("appURL2"));
		driver.manage().window().maximize();
	  } 
	  
	*/  

	  @AfterClass (groups= {"Base"})
	  public void tearDown() {
		  driver.quit();
	  }
	  
	  
	  @SuppressWarnings("deprecation")
	  public String randomString() {
		  String generatedstring = RandomStringUtils.randomAlphabetic(6);
		return generatedstring;
	  }
	  
	  @SuppressWarnings("deprecation")
	  public String randomNumber() {
	  	  String generatedNumber = RandomStringUtils.randomNumeric(6);
	  	return generatedNumber;
	    }
	  
	  @SuppressWarnings("deprecation")
	  public String randomAlphaNumeric() {
	  	  String generatedAlpaNumeric = RandomStringUtils.randomAlphanumeric(5);
	  	return generatedAlpaNumeric;
	    }
	  
	  
	  public String captureScreen(String tname) throws IOException {
		    
		    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		    
		    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		    
		    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + timeStamp + ".png";
		    File targetFile = new File(targetFilePath);
		    
		    sourceFile.renameTo(targetFile);
		    
		    return targetFilePath;
		}



}
