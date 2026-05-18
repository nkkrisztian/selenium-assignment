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

import pages.HomePage;

public class HomeTests extends BaseTests {
	@Test
	public void testPageTitle() {
		HomePage homePage = new HomePage(this.driver);
		assertTrue("Index page should have title.", homePage.getTitle().equals("ParaBank | Welcome | Online Banking"));
	}

	@Test
	public void testStaticServicesPage() {
		HomePage homePage = new HomePage(this.driver);
		homePage.clickServices();
		String bodyText = homePage.getBodyText();
		assertTrue("Bookstore is present", bodyText.contains("Available Bookstore SOAP services"));
		assertTrue("Bookstore services are listed", bodyText.contains("Parasoft Bookstore Web service with a database backend."));
	}

	@Test
	public void testHistoryNavigation() {
		HomePage page = new HomePage(this.driver);
		assertTrue("Home page title shoudld be: ParaBank | Welcome | Online Banking", page.getTitle().equals("ParaBank | Welcome | Online Banking"));
		page.clickServices();
		assertTrue("Services page title should be: ParaBank | Services", page.getTitle().equals("ParaBank | Services"));
		driver.navigate().back();
		assertTrue("Going back in history should return home page.", page.getTitle().equals("ParaBank | Welcome | Online Banking"));
		driver.navigate().forward();
		assertTrue("Going forward in history should return services page.", page.getTitle().equals("ParaBank | Services"));
	}
}

