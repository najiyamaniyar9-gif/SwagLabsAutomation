

package com.najiya.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;

import com.najiya.utils.ConfigReader;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class CheckoutStepDefinitions {

    private WebDriver driver;
    private WebDriverWait wait;

    // Get browser started by LoginStepDefinitions
    private void initializeDriver() {

        driver = BrowserContext.getDriver();

        if (driver == null) {
            throw new IllegalStateException(
                "Browser not initialized. Login must run first."
            );
        }

        wait = new WebDriverWait(
            driver, Duration.ofSeconds(15));
    }

    // Step 1: Open shopping cart
    @When("User opens shopping cart")
    public void openCart() throws InterruptedException {

        initializeDriver();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.className("shopping_cart_link")
        )).click();

        ConfigReader.pause();
    }

    // Step 2: Click Checkout
    @When("User clicks checkout button")
    public void clickCheckout() throws InterruptedException {

        initializeDriver();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("checkout")
        )).click();

        ConfigReader.pause();
    }

    // Step 3: Enter first name
    @When("User enters checkout first name {string}")
    public void enterFirstName(String firstName)
            throws InterruptedException {

        initializeDriver();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("first-name")
        )).sendKeys(firstName);

        ConfigReader.pause();
    }

    // Step 4: Enter last name
    @When("User enters checkout last name {string}")
    public void enterLastName(String lastName)
            throws InterruptedException {

        initializeDriver();

        driver.findElement(By.id("last-name"))
            .sendKeys(lastName);

        ConfigReader.pause();
    }

    // Step 5: Enter postal code
    @When("User enters checkout postal code {string}")
    public void enterPostalCode(String postalCode)
            throws InterruptedException {

        initializeDriver();

        driver.findElement(By.id("postal-code"))
            .sendKeys(postalCode);

        ConfigReader.pause();
    }

    // Step 6: Click Continue
    @When("User clicks continue button")
    public void clickContinue()
            throws InterruptedException {

        initializeDriver();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("continue")
        )).click();

        ConfigReader.pause();
    }

    // Step 7: Click Finish
    @When("User clicks finish button")
    public void clickFinish()
            throws InterruptedException {

        initializeDriver();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("finish")
        )).click();

        ConfigReader.pause();
    }

    // Step 8: Verify order confirmation
    @Then("User should see order confirmation")
    public void verifyOrderConfirmation()
            throws InterruptedException {

        initializeDriver();

        String message = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.className("complete-header")
            )
        ).getText();

        Assert.assertEquals(
            message, "Thank you for your order!");

        System.out.println(
            "Cucumber Checkout Test Passed!");

        ConfigReader.pause();
    }
}



