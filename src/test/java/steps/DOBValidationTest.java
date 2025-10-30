package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DOBValidationTest {

    WebDriver driver;
    WebDriverWait wait;

    @Given("I open the checker to the date of birth page")
    public void openDOBPage() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        driver.get("https://services.nhsbsa.nhs.uk/check-for-help-paying-nhs-costs/start");

        // Accept cookies if visible
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("nhsuk-cookie-banner__link_accept_analytics"))).click();
        } catch (Exception ignored) {}

        // Start now
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Country of residence - England
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-england']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // GP in Scotland or Wales? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Dental practice country - England
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-england']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();
        //  Now on DOB page
    }

    @When("I continue without entering a date of birth")
    public void continueWithoutDOB() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();
    }

    @Then("I should see a date of birth validation error")
    public void validateDOBError() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("li.nhsuk-error-message")
        ));

        String errorText = error.getText().trim().toLowerCase();
        Assert.assertTrue("Expected DOB validation message not present",
                errorText.contains("enter your date of birth"));

        System.out.println("Validation message displayed: " + error.getText());


    } // Validates error message comes up and displays "Enter you DOB"
}
