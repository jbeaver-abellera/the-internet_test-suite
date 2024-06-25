import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BaseTest;

import static com.google.common.base.Throwables.getStackTraceAsString;
import static utils.Constants.WEBPAGE;

public class FormAuthentication extends BaseTest {

    private WebElement username;
    private WebElement password;
    private WebElement loginBtn;
    private WebElement logoutBtn;
    private String alertMessage;
    private String formlinkXPath = "//*[@id=\"content\"]/ul/li[21]/a";

    @BeforeTest
    public void init(){
        BaseTest.setup(WEBPAGE, formlinkXPath);
    }

    @Test
    public void wronguser(){
        try {
            username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            loginBtn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));

            String wronguser = "wr0ngus3r";
            username.sendKeys(wronguser);

            String correctpass = "SuperSecretPassword!";
            password.sendKeys(correctpass);

            loginBtn.click();

            alertMessage = driver.findElement(By.xpath("//*[@id=\"flash\"]")).getText();

            Assert.assertTrue(alertMessage.equals("Your username is invalid!\n×"), "Error on wronguser test. actual alert is successful.");
        }
        catch (NoSuchElementException e) {
            // Capture the stack trace
            String stackTrace = getStackTraceAsString(e);
            Assert.fail("One or more elements could not be found using the provided locators. Stack trace: %s".formatted(stackTrace));
        }
    }
    @Test
    public void wrongpass(){
        try {
            username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            loginBtn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));

            String wronguser = "tomsmith";
            username.sendKeys(wronguser);

            String correctpass = "wrongpassword";
            password.sendKeys(correctpass);

            loginBtn.click();

            alertMessage = driver.findElement(By.xpath("//*[@id=\"flash\"]")).getText();

            Assert.assertTrue(alertMessage.equals("Your password is invalid!\n×"), "Error on wrongpass test. actual alert is successful.");
        }
        catch (NoSuchElementException e) {
            // Capture the stack trace
            String stackTrace = getStackTraceAsString(e);
            Assert.fail("One or more elements could not be found using the provided locators. Stack trace: %s".formatted(stackTrace));
        }
    }

    @Test
    public void correct (){
        try {
            username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            loginBtn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));

            String correctuser = "tomsmith";
            username.sendKeys(correctuser);

            String correctpass = "SuperSecretPassword!";
            password.sendKeys(correctpass);

            loginBtn.click();

            alertMessage = driver.findElement(By.xpath("//*[@id=\"flash\"]")).getText();

            logoutBtn = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a"));

            logoutBtn.click();

            Assert.assertTrue(alertMessage.equals("You logged into a secure area!\n×"), "Error on correct test. actual alert is not successful.");
        }
        catch (NoSuchElementException e) {
            // Capture the stack trace
            String stackTrace = getStackTraceAsString(e);
            Assert.fail("One or more elements could not be found using the provided locators. Stack trace: %s".formatted(stackTrace));
        }
    }
}
