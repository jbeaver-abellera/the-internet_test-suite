package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;
public class WebDriverSingleton {

    private static WebDriver driver;

    // Private constructor to prevent instantiation from outside
    private WebDriverSingleton() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    }

    // Static method to get the singleton instance of WebDriver
    public static WebDriver getDriverInstance() {
        if (driver == null) {
            new WebDriverSingleton(); // Create WebDriver instance
            }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

