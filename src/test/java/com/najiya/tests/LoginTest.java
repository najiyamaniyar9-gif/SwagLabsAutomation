package com.najiya.tests;

import com.najiya.base.BaseTest;
import com.najiyapage.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        Thread.sleep(2000);

        loginPage.enterUsername("standard_user");
        Thread.sleep(2000);

        loginPage.enterPassword("secret_sauce");
        Thread.sleep(2000);

        loginPage.clickLogin();

        String actualHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".title")
                )
        ).getText();

        Assert.assertEquals(actualHeading, "Products");

        Thread.sleep(3000);
    }

    @Test
    public void invalidPasswordTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        Thread.sleep(2000);

        loginPage.enterUsername("standard_user");
        Thread.sleep(2000);

        loginPage.enterPassword("wrong_password");
        Thread.sleep(2000);

        loginPage.clickLogin();

        String actualError = loginPage.getErrorMessage();

        Assert.assertEquals(
                actualError,
                "Epic sadface: Username and password do not match any user in this service"
        );

        Thread.sleep(3000);
    }
    @Test
    public void emptyUsernameTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        Thread.sleep(2000);

        // Username khali rakho, sirf password enter karo
        loginPage.enterPassword("secret_sauce");

        Thread.sleep(2000);

        loginPage.clickLogin();

        String actualError = loginPage.getErrorMessage();

        Assert.assertEquals(
                actualError,
                "Epic sadface: Username is required",
                "Empty username par expected error nahi dikha"
        );

        Thread.sleep(3000);
    }
}