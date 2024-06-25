package utils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static utils.Constants.WEBPAGE;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseTest {
    protected static WebDriver driver;
    protected static String browserInformation;
    protected static byte[] screenshot;
    @BeforeSuite
    public static void setupDriver() {
        driver = WebDriverSingleton.getDriverInstance();
    }

    public static void headersVisible(){
        WebElement header = driver.findElement(By.xpath("/html/body/div[2]/a"));
        WebElement footer = driver.findElement(By.xpath("//*[@id=\"page-footer\"]/div/div/a"));
        boolean isHeaderVisible = header.isDisplayed();
        boolean isFooterVisible = footer.isDisplayed();
        Assert.assertTrue(isHeaderVisible && isFooterVisible);
    }
    public static int getHTTPResponse(String link, String requestMethod) {
        try {
            // URL to send the request to
            URL url = new URL(link);

            // Create a connection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod(requestMethod);

            // Get response code
            int responseCode = connection.getResponseCode();
            return responseCode;
//                // Read the response
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//
//                // Print the response
//                return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void setup(String url, String linkXPath) {
        driver.get(url);
        headersVisible();
        WebElement Link = driver.findElement(By.xpath(linkXPath));
        Link.click();
        System.out.println("Title: " + driver.getTitle());
        headersVisible();
    }


    @AfterSuite
    public static void teardown() {
        WebDriverSingleton.quitDriver();
    }

    private static String getBrowserInformation() {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = capabilities.getBrowserName();
        String browserVersion = capabilities.getBrowserVersion();
        return "Browser Name: " + browserName + ", Browser Version: " + browserVersion;
    }

    private static byte[] getScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
