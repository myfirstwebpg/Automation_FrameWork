package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver driver;
	
	public BasePage (WebDriver driver) {
		this.driver  = driver; // ← assigning the parameter to the class variable
		PageFactory.initElements(driver, this);
	}
	
	public void safeClick(WebElement element) {
        try {
            element.click();  // Try normal click
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            js.executeScript("arguments[0].click();", element);  // JS click fallback
        }
        
	}
}
