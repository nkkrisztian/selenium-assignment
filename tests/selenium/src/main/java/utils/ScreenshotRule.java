package utils;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utils.Config;

public class ScreenshotRule extends TestWatcher {
    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (driver != null) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String fileName = Config.getProperty("directory.screenshots") 
                                + description.getMethodName() 
                                + "_" + timestamp 
                                + ".png";
                
                FileUtils.copyFile(srcFile, new File(fileName));
                System.out.println("Screenshot taken for failed test: " + description.getMethodName() + " at " + timestamp);
            } catch (IOException ioe) {
                System.err.println("Failed to capture screenshot: " + ioe.getMessage());
            }
        }
    }

    @Override
    protected void finished(Description description) {
        if (driver != null) {
            driver.quit();
        }
    }
}
