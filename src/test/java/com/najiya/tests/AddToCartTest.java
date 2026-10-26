

package com.najiya.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.najiya.base.BaseTest;

public class AddToCartTest extends BaseTest {

    @Test
    public void verifyMultipleAddToCart()
            throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(10));

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("user-name")
        )).sendKeys("standard_user");

        Thread.sleep(0);

        driver.findElement(By.id("password"))
            .sendKeys("secret_sauce");

        Thread.sleep(0);

        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("inventory_container")
        ));

        Thread.sleep(2000);

        ProductPage productPage = new ProductPage(driver);

        // Step 2: Add first product
        productPage.addBackpack();
        System.out.println("Backpack Added");

        Thread.sleep(0);

        // Step 3: Add second product
        productPage.addBikeLight();
        System.out.println("Bike Light Added");

        Thread.sleep(0);

        // Step 4: Add third product
        productPage.addTShirt();
        System.out.println("T-Shirt Added");

        Thread.sleep(0);

System.out.println("Actual Cart Count: "
    + productPage.getCartCount());

System.out.println("Backpack Remove Button: "
    + driver.findElements(
        By.id("remove-sauce-labs-backpack")).size());

System.out.println("Bike Light Remove Button: "
    + driver.findElements(
        By.id("remove-sauce-labs-bike-light")).size());

System.out.println("T-Shirt Remove Button: "
    + driver.findElements(
        By.id("remove-sauce-labs-bolt-t-shirt")).size());


        // Step 5: Verify cart count
        Assert.assertEquals(
            productPage.getCartCount(), "3");

        System.out.println("Cart Count Verified: 3");

        // Step 6: Open cart
        productPage.openCart();

        Thread.sleep(0);

        // Step 7: Verify all three products
        Assert.assertEquals(
            driver.findElements(
                By.className("cart_item")).size(), 3);

        System.out.println("All 3 Products Verified!");
    }

@Test
public void verifyRemoveProduct()
        throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(
        driver, Duration.ofSeconds(10));

    // Step 1: Login
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.id("user-name")
    )).sendKeys("standard_user");

    Thread.sleep(0);

    driver.findElement(By.id("password"))
        .sendKeys("secret_sauce");

    Thread.sleep(2000);

    driver.findElement(By.id("login-button")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.id("inventory_container")
    ));

    ProductPage productPage = new ProductPage(driver);

    // Step 2: Add 3 products
    productPage.addBackpack();
    Thread.sleep(2000);

    productPage.addBikeLight();
    Thread.sleep(2000);

    productPage.addTShirt();
    Thread.sleep(2000);

    // Step 3: Verify cart count = 3
    Assert.assertEquals(
        productPage.getCartCount(), "3");

    // Step 4: Remove Bike Light
    productPage.removeBikeLight();

    Thread.sleep(2000);

    // Step 5: Verify cart count = 2
    productPage.verifyCartCount("2");

    Assert.assertEquals(
        productPage.getCartCount(), "2");

    // Step 6: Open cart
    productPage.openCart();

    Thread.sleep(3000);

    // Step 7: Verify 2 products remain
    Assert.assertEquals(
        driver.findElements(
            By.className("cart_item")).size(), 2);

    // Step 8: Verify Bike Light is removed
    Assert.assertEquals(
        driver.findElements(
            By.xpath(
                "//div[@class='inventory_item_name' " +
                "and text()='Sauce Labs Bike Light']"
            )).size(), 0);

    System.out.println("Remove Product Test Passed!");
}

}
