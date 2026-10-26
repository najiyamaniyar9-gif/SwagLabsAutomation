

package com.najiya.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.najiya.base.BaseTest;

public class LoginDataProviderTest extends BaseTest {

    // Test Data
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {

        return new Object[][] {

            {"standard_user", "secret_sauce", true},

            {"locked_out_user", "secret_sauce", false},

            {"standard_user", "wrong123", false},

            {"", "secret_sauce", false}
        };
    }

    // Login Test
    @Test(dataProvider = "loginData")
    public void verifyLoginWithMultipleData(
            String username,
            String password,
            boolean shouldLogin)
            throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

        // Enter Username
        driver.findElement(By.id("user-name"))
                .sendKeys(username);

        Thread.sleep(0);

        // Enter Password
        driver.findElement(By.id("password"))
                .sendKeys(password);

        Thread.sleep(0);

        // Click Login
        driver.findElement(By.id("login-button"))
                .click();

        Thread.sleep(0);

        if (shouldLogin) {

            // Verify successful login
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.id("inventory_container")));

            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"));

            System.out.println("Valid Login Passed");

        } else {

            // Verify error message
            String errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )).getText();

            Assert.assertFalse(errorMessage.isEmpty());

            System.out.println(
                "Invalid Login Test Passed: " + username);
        }
    }
}



