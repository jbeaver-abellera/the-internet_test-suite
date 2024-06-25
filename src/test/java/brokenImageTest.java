import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BaseTest;
import static utils.Constants.WEBPAGE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Throwables.getStackTraceAsString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class brokenImageTest extends BaseTest {
    public static final String GET_METHOD = "GET";
    private String brokenimageLinkXPath = "//*[@id=\"content\"]/ul/li[4]/a";

    @BeforeTest
    public void Setup() {
        BaseTest.setup(WEBPAGE, brokenimageLinkXPath);
        log.info("Redirecting to broken image hyperlink.");
    }

    @Test
    public static void checkBrokenImages(){
        List<WebElement> images = driver.findElements(By.tagName("img"));

        for (WebElement img : images) {
            String imgLink = img.getAttribute("src");
            int imgLinkResponse = BaseTest.getHTTPResponse(imgLink, GET_METHOD);
            String errMessage = String.format("Error Image below not found:\n%s", imgLink);
            Assert.assertEquals(imgLinkResponse, 200, errMessage);
        }
    }

}
