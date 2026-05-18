import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Dimension;

import java.net.URL;
import java.net.MalformedURLException;

import utils.Config;
import utils.ScreenshotRule;
import pages.AdminPage;

public class BaseTests {

	protected WebDriver driver;
	private static boolean dbInitialized = false;

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
		boolean isHeadless = Boolean.parseBoolean(Config.getProperty("browser.headless"));
		if (isHeadless) {
			options.addArguments("--headless"); 
		} else {
			options.addArguments("--start-maximized");
		}

		this.driver = new RemoteWebDriver(new URL(Config.getProperty("selenium.hub.url")), options);
		screenshotRule.setDriver(driver);

		if (isHeadless) {
			this.driver.manage().window().setSize(new Dimension(1920, 1080));
		} 

		if (!dbInitialized) {
			initializeDB();
			dbInitialized = true;
		System.out.println("-------------------- DB INITIALIZED ------------------");
		}
    }

	private void initializeDB() {
		AdminPage admin = new AdminPage(this.driver);
        admin.clickInitializeDB();
        admin.clickCleanDatabase();
	}
}

