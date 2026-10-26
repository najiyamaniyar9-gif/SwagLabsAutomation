package com.najiya.tests;

import com.najiya.base.BaseTest;
import com.najiyapage.LoginPage;
import com.najiyapage.ProductsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    @Test
    public void addBackpackToCartTest()
            throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("standard_user");
        Thread.sleep(2000);

        loginPage.enterPassword("secret_sauce");
        Thread.sleep(2000);

        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getPageHeading(),
                "Products",
                "Products page open nahi hua"
        );

        Thread.sleep(2000);

        productsPage.addBackpackToCart();

        Assert.assertEquals(
                productsPage.getCartCount(),
                "1",
                "Cart count 1 hona chahiye"
        );

        Thread.sleep(3000);
    }
}