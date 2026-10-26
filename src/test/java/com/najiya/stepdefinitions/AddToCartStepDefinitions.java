
package com.najiya.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.testng.Assert;

import com.najiya.utils.ConfigReader;

public class AddToCartStepDefinitions {

    // Shared browser driver
    private WebDriver driver;
    private WebDriverWait wait;

private void initializeDriver() {

    driver = BrowserContext.getDriver();

    if (driver == null) {
        throw new IllegalStateException(
            "Browser not initialized. Login step must run first."
        );
    }

    wait = new WebDriverWait(
        driver, Duration.ofSeconds(15));
}


    

    @When("User adds Backpack to cart")
    public void addBackpack()
            throws InterruptedException {
    	initializeDriver();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("add-to-cart-sauce-labs-backpack")
        )).click();

        ConfigReader.pause();
    }

    @When("User adds Bike Light to cart")
    public void addBikeLight()
            throws InterruptedException {
    	initializeDriver();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("add-to-cart-sauce-labs-bike-light")
        )).click();

        ConfigReader.pause();
    }

    @When("User adds Bolt T-Shirt to cart")
    public void addTShirt()
            throws InterruptedException {
    	initializeDriver();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("add-to-cart-sauce-labs-bolt-t-shirt")
        )).click();

        ConfigReader.pause();
    }

    @Then("Cart should display 3 products")
    public void verifyCartCount()
            throws InterruptedException {
    	initializeDriver();

        String count = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.className("shopping_cart_badge")
            )).getText();

        Assert.assertEquals(count, "3");

        ConfigReader.pause();

        System.out.println(
            "Cucumber Add to Cart Test Passed!");
    }

@When("User removes Bike Light from cart")
public void removeBikeLight()
        throws InterruptedException {

    initializeDriver();

    wait.until(ExpectedConditions.elementToBeClickable(
        By.id("remove-sauce-labs-bike-light")
    )).click();

    ConfigReader.pause();

    System.out.println("Bike Light Removed!");
}

@Then("Cart should display 2 products")
public void verifyTwoProducts()
        throws InterruptedException {

    initializeDriver();

    String count = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.className("shopping_cart_badge")
        )).getText();

    Assert.assertEquals(count, "2");

    ConfigReader.pause();

    System.out.println("Cart Count Verified: 2");
}

}
