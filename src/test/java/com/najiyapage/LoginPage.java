


	package com.najiyapage;

	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class LoginPage {

	    private final WebDriver driver;
	    private final WebDriverWait wait;

	    // Login page ke locators
	    private final By usernameField = By.id("user-name");
	    private final By passwordField = By.id("password");
	    private final By loginButton = By.id("login-button");
	    private final By errorMessage =
	            By.cssSelector("[data-test='error']");

	    // Test ka existing browser receive karta hai
	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(
	                driver, Duration.ofSeconds(15)
	        );
	    }

	    public void enterUsername(String username) {
	        wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        usernameField
	                )
	        ).sendKeys(username);
	    }

	    public void enterPassword(String password) {
	        wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        passwordField
	                )
	        ).sendKeys(password);
	    }

	    public void clickLogin() {
	        wait.until(
	                ExpectedConditions.elementToBeClickable(
	                        loginButton
	                )
	        ).click();
	    }

	    public String getErrorMessage() {
	        return wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        errorMessage
	                )
	        ).getText();
	    }
	}


