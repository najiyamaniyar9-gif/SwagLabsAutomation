

package com.najiyapage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(
            driver, Duration.ofSeconds(10));
    }

    // Click Checkout button
    public void clickCheckout() {

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("checkout"))).click();
    }

    // Enter First Name
    public void enterFirstName(String firstName) {

        driver.findElement(By.id("first-name"))
            .sendKeys(firstName);
    }

    // Enter Last Name
    public void enterLastName(String lastName) {

        driver.findElement(By.id("last-name"))
            .sendKeys(lastName);
    }

    // Enter Postal Code
    public void enterPostalCode(String postalCode) {

        driver.findElement(By.id("postal-code"))
            .sendKeys(postalCode);
    }

    // Click Continue button
    public void clickContinue() {

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("continue"))).click();
    }

    // Click Finish button
    public void clickFinish() {

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("finish"))).click();
    }

    // Get Order Confirmation Message
    public String getConfirmationMessage() {

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.className("complete-header")
            )).getText();
    }
}



