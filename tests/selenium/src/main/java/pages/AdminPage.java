package pages;

import org.openqa.selenium.*;

import java.net.URL;

import utils.Config;

public class AdminPage extends PageBase {
	public AdminPage(WebDriver driver) {
		super(driver);
		this.driver.get(Config.getUrl("admin"));
	}

	public void clickInitializeDB() {
		WebElement initButton = waitVisibilityAndReturnElement(By.xpath("//form[@name='initializeDB']//button[@value='INIT']"));
		initButton.click();
	}

	public void clickCleanDatabase() {
		WebElement cleanButton = waitVisibilityAndReturnElement(By.xpath("//form[@name='initializeDB']//button[@value='CLEAN']"));
		cleanButton.click();
	}

	public void setJMS() {
		if (getBodyText().contains("stopped"))
		{
			WebElement startupJMSButton = waitVisibilityAndReturnElement(By.xpath("//form[@name='toggleJms']//input[@class='button' and @type='submit' and @value='Startup']"));
			startupJMSButton.click();
		}
	}
}
