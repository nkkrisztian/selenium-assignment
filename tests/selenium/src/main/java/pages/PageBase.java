package pages;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;

public class PageBase {
    protected WebDriver driver;
    private WebDriverWait wait;

	public PageBase(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
	}

    protected WebElement waitVisibilityAndReturnElement(By locator) {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

	protected WebElement waitUntilClickableAndReturnElement(By locator) {
		return this.wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public String getBodyText() {
		return waitVisibilityAndReturnElement(By.tagName("body")).getText();
	}

	public String getElementText(By locator) {
		return waitVisibilityAndReturnElement(locator).getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void inputText(By locator, String keys) {
		waitUntilClickableAndReturnElement(locator).sendKeys(keys);
	}

	public void clickElement(By locator) {
		waitUntilClickableAndReturnElement(locator).click();
	}
}

