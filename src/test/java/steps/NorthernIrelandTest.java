package steps;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class NorthernIrelandTest {

    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the NHS cost checker start page for NI")
    public void openStartForNI() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        driver.get("https://services.nhsbsa.nhs.uk/check-for-help-paying-nhs-costs/start");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("nhsuk-cookie-banner__link_accept_analytics"))).click();
        } catch (Exception ignored) {}

        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();
    }

    @When("I select I live in Northern Ireland")
    public void selectNorthernIreland() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-nire']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();
    }

    @Then("I should be told this service does not proceed for Northern Ireland")
    public void assertNIMessage() {
        String text = driver.findElement(By.tagName("body")).getText().toLowerCase();
        Assert.assertTrue(
                "Expected NI restriction message not shown",
                text.contains("northern ireland") &&
                        (text.contains("cannot") || text.contains("not available") || text.contains("cannot use this service"))
        );
    }

    @Then("I should not be taken to the GP practice question")
    public void assertNotGPPage() {
        String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();
        Assert.assertFalse(
                "Incorrectly navigated to the GP practice question page.",
                pageText.contains("is your gp practice in scotland or wales")
        );
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}

