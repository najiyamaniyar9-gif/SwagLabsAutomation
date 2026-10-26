package com.najiyapage;



	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class ProductsPage {

	    private final WebDriver driver;
	    private final WebDriverWait wait;

	    private final By pageHeading =
	            By.cssSelector(".title");

	    private final By backpackAddButton =
	            By.id("add-to-cart-sauce-labs-backpack");

	    private final By cartBadge =
	            By.cssSelector(".shopping_cart_badge");

	    public ProductsPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(
	                driver, Duration.ofSeconds(15)
	        );
	    }

	    public String getPageHeading() {
	        return wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        pageHeading
	                )
	        ).getText();
	    }

	    public void addBackpackToCart() {
	        wait.until(
	                ExpectedConditions.elementToBeClickable(
	                        backpackAddButton
	                )
	        ).click();
	    }

	    public String getCartCount() {
	        return wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        cartBadge
	                )
	        ).getText();
	    }
	}


