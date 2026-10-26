
package com.najiya.base;

import java.time.Duration;
import com.najiya.utils.ConfigReader;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        // Disable password manager
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Disable password leak warnings
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        options.addArguments(
            "--disable-features=PasswordLeakDetection"
        );

        // Launch Chrome
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        ConfigReader.pause();

        wait = new WebDriverWait(
            driver, Duration.ofSeconds(15));

        // Open SauceDemo website
        driver.get(ConfigReader.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

public WebDriver getDriver() {
    return driver;
}

}
