import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;

import pages.HomePage;
import utils.UserFactory;
import utils.UserInfo;
import utils.Config;

public class AccountTests extends BaseTests {

	public static UserInfo userInfo = UserFactory.createUserInfo();
	public static boolean userRegistered = false;

	@Before
    @Override
    public void setup() throws MalformedURLException {
        super.setup();

        driver.get(Config.getUrl("home"));
        if (!userRegistered) {
            HomePage page = new HomePage(this.driver);
            page.sendRegistrationForm(userInfo);
            page.clickLogOut();
            userRegistered = true;
			driver.get(Config.getUrl("home"));
        }
    }

	@Test
	public void tryLoginWithInvalidCredentials() {
		HomePage page = new HomePage(this.driver);
		page.sendLoginForm("invalid_username", "invalid_password");
		assertTrue("Trying to send invalid credentials should result in error.", page.getBodyText().contains("Error!"));
	}

	@Test
	public void loginAsRegisteredUser() {
		HomePage page = new HomePage(this.driver);
		page.sendLoginForm(userInfo.username, userInfo.password);
		assertTrue("User should be able to log in successfuly.", page.isLogoutButtonVisible());;
	}

	@Test
	public void logOut() {
		HomePage page = new HomePage(this.driver);
		page.sendLoginForm(userInfo.username, userInfo.password);
		page.clickLogOut();
		assertTrue("User should see login form after logout.", page.isLoginButtonVisible());
	}

	@Test
	public void OpenBankAccount() {
		HomePage page = new HomePage(this.driver);
		page.sendLoginForm(userInfo.username, userInfo.password);
		page.clickOpenAccount();
		List<String> accountOptions = page.getSourceAccountOptions();
		assertTrue("Source account options list should not be empty.", accountOptions.size() > 0);
		page.openNewAccount("SAVINGS", accountOptions.get(0));
		assertTrue("User should be able to open new saving account.", page.getBodyText().contains("Account Opened!"));
		page.clickLogOut();
	}
}
