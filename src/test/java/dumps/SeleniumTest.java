package dumps;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Throwables.getStackTraceAsString;

public class SeleniumTest {

    public static WebDriver driver;
    @BeforeTest
    public static void Setup() {
        EdgeOptions options = new EdgeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/");
        System.out.println("Title: " + driver.getTitle());
//        https://www.jetbrains.com/help/idea/getting-started.html
//        https://shopee.ph/
    }

    @Test
    public static void brokenImageTest(){
        try {
            WebElement brokenImageLink = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[4]/a"));
            brokenImageLink.click();

            WebElement poweredBy = driver.findElement(By.xpath("//*[@id=\"page-footer\"]/div"));
        }
        catch (NoSuchElementException e) {
        // Capture the stack trace
        String stackTrace = getStackTraceAsString(e);
        Assert.fail("One or more elements could not be found using the provided locators. Stack trace: %s".formatted(stackTrace));
        }
    }

//    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
