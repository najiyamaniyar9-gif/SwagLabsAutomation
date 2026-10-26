package com.najiyapage;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.najiya.base.BaseTest;
import com.najiya.tests.ProductPage;


public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCompleteCheckout()
            throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(15));

        // Step 1: Login
        wait.until(ExpectedConditions
            .visibilityOfElementLocated(
                By.id("user-name")))
            .sendKeys("standard_user");

        Thread.sleep(2000);

        driver.findElement(By.id("password"))
            .sendKeys("secret_sauce");

        Thread.sleep(2000);

        driver.findElement(By.id("login-button"))
            .click();

        wait.until(ExpectedConditions
            .visibilityOfElementLocated(
                By.id("inventory_container")));

        // Step 2: Add products
        ProductPage productPage =
            new ProductPage(driver);

        productPage.addBackpack();
        Thread.sleep(2000);

        productPage.addBikeLight();
        Thread.sleep(2000);

        // Step 3: Verify cart count
        Assert.assertEquals(
            productPage.getCartCount(), "2");

        // Step 4: Open Cart
        productPage.openCart();

        Thread.sleep(2000);

        // Step 5: Start Checkout
        CheckoutPage checkoutPage =
            new CheckoutPage(driver);

        checkoutPage.clickCheckout();

        Thread.sleep(2000);

        // Step 6: Fill customer details
        checkoutPage.enterFirstName("Najiya");

        Thread.sleep(1000);

        checkoutPage.enterLastName("Maner");

        Thread.sleep(1000);

        checkoutPage.enterPostalCode("411033");

        Thread.sleep(2000);

        // Step 7: Continue
        checkoutPage.clickContinue();

        Thread.sleep(2000);

        // Step 8: Finish order
        checkoutPage.clickFinish();

        // Step 9: Verify confirmation
        String message =
            checkoutPage.getConfirmationMessage();

        Assert.assertEquals(
            message, "Thank you for your order!");

        System.out.println(
            "Checkout Test Passed!");
    }
}



