import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Throwables.getStackTraceAsString;
import static utils.Constants.WEBPAGE;

public class Dropdown extends BaseTest {
    private String dropdownLinkXPath = "//*[@id=\"content\"]/ul/li[11]/a";
    public static List<String> expectedDropdownValues = Arrays.asList("Please select an option", "Option 1", "Option 2");

    @BeforeTest
    public void dropdownSetup(){
        BaseTest.setup(WEBPAGE, dropdownLinkXPath);
    }

    @Test
    public static void checkDropdownOptions (){
        WebElement dropdownElement = driver.findElement(By.xpath("//*[@id=\"dropdown\"]"));
        dropdownElement.click();
        Assert.assertTrue(dropdownElement.isDisplayed());

        // Create a Select object
        Select dropdown = new Select(dropdownElement);

        // Retrieve all options from the dropdown
        List<WebElement> dropdownOptions = dropdown.getOptions();

        // Extract the text from each option and store them in a list
        List<String> actualDropdownValues = new ArrayList<>();
        for (WebElement dropdownOption : dropdownOptions) {
            actualDropdownValues.add(dropdownOption.getText());
        }

        // Compare the lists
        Assert.assertTrue(actualDropdownValues.equals(expectedDropdownValues), "Erorr: Dropdown has missing or incorrect values.");
    }
}
