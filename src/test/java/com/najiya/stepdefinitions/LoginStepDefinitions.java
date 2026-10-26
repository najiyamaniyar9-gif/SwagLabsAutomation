
package com.najiya.stepdefinitions;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;

import com.najiya.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;

public class LoginStepDefinitions {

    WebDriver driver;
    WebDriverWait wait;

    @Given("User is on SauceDemo login page")
    public void openLoginPage() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", Map.of(
            "credentials_enable_service", false,
            "profile.password_manager_enabled", false,
            "profile.password_manager_leak_detection", false
        ));

        options.addArguments(
            "--disable-features=PasswordLeakDetection");

        driver = new ChromeDriver(options);
        BrowserContext.setDriver(driver);
        driver.manage().window().maximize();

        wait = new WebDriverWait(
            driver, Duration.ofSeconds(15));

        driver.get("https://www.saucedemo.com/");

        ConfigReader.pause();
    }

    @When("User enters username {string}")
    public void enterUsername(String username)
            throws InterruptedException {

        wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.id("user-name")))
            .sendKeys(username);

        ConfigReader.pause();
    }

    @When("User enters password {string}")
    public void enterPassword(String password)
            throws InterruptedException {

        driver.findElement(By.id("password"))
            .sendKeys(password);

        ConfigReader.pause();
    }

    @When("User clicks login button")
    public void clickLogin()
            throws InterruptedException {

        driver.findElement(By.id("login-button")).click();

        ConfigReader.pause();
    }

    @Then("User should see products page")
    public void verifyProductsPage()
            throws InterruptedException {

        wait.until(ExpectedConditions
            .visibilityOfElementLocated(
                By.id("inventory_container")));

        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory.html"));

        ConfigReader.pause();
    }

    @Then("User should see login error message")
    public void verifyErrorMessage()
            throws InterruptedException {

        String error = wait.until(ExpectedConditions
            .visibilityOfElementLocated(
                By.cssSelector("[data-test='error']")))
            .getText();

        Assert.assertFalse(error.isEmpty());

        Thread.sleep(3000);
    }

    @After
    public void closeBrowser() {

        if (driver != null) {
            driver.quit();
            BrowserContext.removeDriver();
            
            driver = null;
        }
    }
}
