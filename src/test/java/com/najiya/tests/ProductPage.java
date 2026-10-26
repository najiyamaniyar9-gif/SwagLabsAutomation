
package com.najiya.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addBackpack() {
        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("add-to-cart-sauce-labs-backpack")
        )).click();
        
    }
    
    
    
    
    
    
    
    
    

    public void addBikeLight() {
        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("add-to-cart-sauce-labs-bike-light")
        )).click();
    }

    public void addTShirt() {
        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("add-to-cart-sauce-labs-bolt-t-shirt")
        )).click();
    }

    public String getCartCount() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.className("shopping_cart_badge")
            )
        ).getText();
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(
            By.className("shopping_cart_link")
        )).click();
    }

public void removeBikeLight() {

    wait.until(ExpectedConditions.elementToBeClickable(
        By.id("remove-sauce-labs-bike-light")
    )).click();

}

public void verifyCartCount(String expectedCount) {

    wait.until(ExpectedConditions.textToBePresentInElementLocated(
        By.className("shopping_cart_badge"),
        expectedCount
    ));

}

}
