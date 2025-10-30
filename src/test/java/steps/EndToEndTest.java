package steps;
// Package I am operating from

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// All of my imports - Dependencies in pom.xml file

public class EndToEndTest {
    // Public class from my steps package. EndToEndTest

    WebDriver driver;
    WebDriverWait wait; // Added wait object

    @Given("I am on the NHS cost checker start page")
    public void openCostChecker() {

        WebDriverManager.chromedriver().clearDriverCache().setup(); // Ensures matching ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(8)); // Wait time added

        driver.get("https://services.nhsbsa.nhs.uk/check-for-help-paying-nhs-costs/start");

        // Accept cookies from the cookie banner if it appears
        try { wait.until(ExpectedConditions.elementToBeClickable(By.id("nhsuk-cookie-banner__link_accept_analytics"))).click(); } catch (Exception ignored) {}

        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click(); // Click Start Now
    }

    @When("I select I live in the UK")
    public void selectUKCountry() {
        // Click England using label instead of hidden input
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-england']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();
    }


    @When("I answer the questions about my circumstances")
    public void answerCircumstances() {

        // Is your GP in Scotland or Wales? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Which country is your dental practice in? England
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-england']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // What is your Date of Birth? Send my DOB 29/12/1990
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dob-day"))).sendKeys("29");
        driver.findElement(By.id("dob-month")).sendKeys("12");
        driver.findElement(By.id("dob-year")).sendKeys("1990");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Do you live with a partner? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Do you claim any benefits? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Are you currently pregnant? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Do you have injury or illness? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Do you have diabetes? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // To check what help you can get, do you have any of these conditions? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Do you have glaucoma? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Do you live permanently in care? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();

        // Do you have more than 16,000 in savings? No
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='radio-no']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button"))).click();
    }

    @Then("I should see whether I get help with NHS costs")
    public void verifyResult() {
        // Should now know if user gets the help.
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result-heading")));

        System.out.println("RESULT DISPLAYED: " + result.getText()); //From the verified result heading
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close browser AFTER scenario finishes
        }
    }
}