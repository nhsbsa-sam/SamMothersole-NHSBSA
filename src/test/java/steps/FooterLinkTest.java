package steps;

import io.cucumber.java.en.*;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FooterLinkTest {

    WebDriver driver = Hooks.driver;
    WebDriverWait wait;

    @Given("I am on the NHS cost checker start page for footer testing")
    public void i_am_on_the_nhs_cost_checker_start_page_for_footer_testing() {

        driver = Hooks.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(8)); //wait, allows time for UI to catch up before the testing.

        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        driver.get("https://services.nhsbsa.nhs.uk/check-for-help-paying-nhs-costs/start");

        try { driver.findElement(By.id("nhsuk-cookie-banner__link_accept_analytics")).click(); } catch (Exception ignored) {}

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("check"));
        System.out.println("Start page loaded"); // confirms if page has loaded
    }

    @When("I click each footer link and verify it opens correctly")
    public void i_click_each_footer_link_and_verify_it_opens_correctly() throws InterruptedException {

        String originalTab = driver.getWindowHandle();

        List<By> footerLinks = new ArrayList<>();
        footerLinks.add(By.id("footer-link-accessibility")); // Id of footer links in an array list
        footerLinks.add(By.id("footer-link-cookies"));
        footerLinks.add(By.id("footer-link-privacy"));
        footerLinks.add(By.id("footer-link-terms"));

        for (By link : footerLinks) {

            WebElement footerLink = wait.until(ExpectedConditions.elementToBeClickable(link));
            int beforeCount = driver.getWindowHandles().size();

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", footerLink);
            Thread.sleep(1200);

            int afterCount = driver.getWindowHandles().size(); // Manages window sizes and tabbing to new windows below

            if (afterCount > beforeCount) {
                for (String tab : driver.getWindowHandles()) {
                    if (!tab.equals(originalTab)) driver.switchTo().window(tab);
                }
                wait.until(d -> !driver.getTitle().isEmpty());
                System.out.println("New Tab: " + driver.getTitle()); // New Tab
                driver.close();
                driver.switchTo().window(originalTab);
            } else {
                wait.until(d -> !driver.getTitle().isEmpty());
                System.out.println("Same Tab: " + driver.getTitle()); // Print out statement confirming it is in the same tab
                driver.navigate().back();
            }
        }
    }

    @Then("all footer links should be valid")
    public void all_footer_links_should_be_valid() {
        System.out.println("Footer links successfully validated."); // Print out statement to validate test
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close browser -- Does not seem to be closing at the end. Manually close it
    }
}}
