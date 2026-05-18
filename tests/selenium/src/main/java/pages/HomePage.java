package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.stream.Collectors;

import java.net.URL;
import utils.Config;
import utils.UserInfo;

public class HomePage extends PageBase {
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver.get(Config.getUrl("home"));
	}

	public void sendLoginForm(String username, String password) {
		inputText(By.xpath("//form[@name='login']//input[@type='text' and @name='username']"), username);
		inputText(By.xpath("//form[@name='login']//input[@type='password' and @name='password']"), password);
		clickElement(By.xpath("//form[@name='login']//input[@type='submit' and @value='Log In']"));
	}

	public String getErrorMsg() {
		return getElementText(By.xpath("//p[@class='error']"));
	}

	public void sendRegistrationForm(UserInfo info) {
		clickElement(By.linkText("Register"));
		inputText(By.id("customer.firstName"), info.firstName);
		inputText(By.id("customer.lastName"), info.lastName);
		inputText(By.id("customer.address.street"), info.street);
		inputText(By.id("customer.address.city"), info.city);
		inputText(By.id("customer.address.state"), info.state);
		inputText(By.id("customer.address.zipCode"), info.zipCode);
		inputText(By.id("customer.phoneNumber"), info.phoneNumber);
		inputText(By.id("customer.ssn"), info.ssn);
		inputText(By.id("customer.username"), info.username);
		inputText(By.id("customer.password"), info.password);
		inputText(By.id("repeatedPassword"), info.password);
		clickElement(By.xpath("//form[@id='customerForm']//input[@type='submit' and @value='Register']"));
	}

	public void clickLogOut() {
		clickElement(By.linkText("Log Out"));
	}

	public void clickServices() {
		clickElement(By.linkText("Services"));
	}

	public void clickOpenAccount() {
		clickElement(By.linkText("Open New Account"));
	}

	public List<String> getSourceAccountOptions() {
		Select sourceAccountSelector = new Select(waitVisibilityAndReturnElement(By.id("fromAccountId")));
		return sourceAccountSelector.getOptions().stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
	}

	public void openNewAccount(String accountType, String sourceAccount) {
        Select accountTypeSelector = new Select(waitVisibilityAndReturnElement(By.id("type")));
        accountTypeSelector.selectByVisibleText(accountType);

        Select sourceAccountSelector = new Select(waitVisibilityAndReturnElement(By.id("fromAccountId")));
        sourceAccountSelector.selectByVisibleText(sourceAccount);

        clickElement(By.xpath("//input[@value='Open New Account']"));
    }
		
	public boolean isLogoutButtonVisible() {
			return waitVisibilityAndReturnElement( By.xpath("//a[text()='Log Out']")).isDisplayed();
	}

	public boolean isLoginButtonVisible() {
			return waitVisibilityAndReturnElement( By.xpath("//form[@name='login']//input[@value='Log In']")).isDisplayed();
	}

}
