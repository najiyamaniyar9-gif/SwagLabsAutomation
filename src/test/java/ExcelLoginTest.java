



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.najiya.base.BaseTest;
import com.najiya.utils.ExcelReader;
import com.najiya.utils.ConfigReader;

public class ExcelLoginTest extends BaseTest {

    @DataProvider(name = "excelLoginData")
    public Object[][] getLoginData() {

        return ExcelReader.getExcelData(
            "src/test/resources/testdata/LoginTestData.xlsx",
            "Sheet1"
        );
    }

    @Test(dataProvider = "excelLoginData")
    public void verifyExcelLogin(
            String username,
            String password,
            String expectedResult)
            throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(15));

        // Enter username
        wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.id("user-name")))
            .sendKeys(username);

        ConfigReader.pause();

        // Enter password
        driver.findElement(By.id("password"))
            .sendKeys(password);

        ConfigReader.pause();

        // Click Login
        driver.findElement(By.id("login-button"))
            .click();

        ConfigReader.pause();

        if (expectedResult.equalsIgnoreCase("success")) {

            // Verify successful login
            wait.until(ExpectedConditions
                .visibilityOfElementLocated(
                    By.id("inventory_container")));

            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"));

        } else if (expectedResult.equalsIgnoreCase("failure")) {

            // Verify login error
            String error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )).getText();

            Assert.assertFalse(error.isEmpty());

        } else {
            Assert.fail(
                "Invalid ExpectedResult in Excel: "
                + expectedResult);
        }

        System.out.println(
            "Excel Login Test Passed: " + username);
    }
}
