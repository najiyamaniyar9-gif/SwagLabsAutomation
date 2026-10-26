
package com.najiya.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.najiya.base.BaseTest;
import com.najiya.reports.ExtentReportManager;

public class ScreenshotListener implements ITestListener {

    // Extent Report object
    private static final ExtentReports extent =
            ExtentReportManager.getReport();

    // Separate report entry for each test
    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    // Runs when test starts
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest currentTest = extent.createTest(
                result.getMethod().getMethodName());

        extentTest.set(currentTest);

        System.out.println(
                "Test Started: " + result.getName());
    }

    // Runs when test passes
    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest currentTest = extentTest.get();

        if (currentTest != null) {
            currentTest.pass("Test Passed Successfully");
        }

        captureScreenshot(result, "PASSED");

        extentTest.remove();
    }

    // Runs when test fails
    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest currentTest = extentTest.get();

        if (currentTest != null) {
            currentTest.fail(result.getThrowable());
        }

        captureScreenshot(result, "FAILED");

        extentTest.remove();
    }

    // Runs when test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest currentTest = extentTest.get();

        if (currentTest != null) {
            currentTest.skip("Test Skipped");

            if (result.getThrowable() != null) {
                currentTest.skip(result.getThrowable());
            }
        }

        extentTest.remove();
    }

    // Method to capture screenshot
    private void captureScreenshot(
            ITestResult result, String status) {

        try {

            BaseTest baseTest =
                    (BaseTest) result.getInstance();

            WebDriver driver = baseTest.getDriver();

            if (driver == null) {
                System.out.println(
                        "Screenshot not captured: Driver is null");
                return;
            }

            // Create screenshots folder
            File folder = new File("screenshots");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Capture screenshot
            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            // Generate screenshot name
            String fileName =
                    result.getName()
                    + "_" + status
                    + "_" + System.currentTimeMillis()
                    + ".png";

            File destination =
                    new File(folder, fileName);

            // Save screenshot
            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println(
                    "Screenshot saved: "
                    + destination.getAbsolutePath());

            // Attach screenshot to Extent Report
            ExtentTest currentTest = extentTest.get();

            if (currentTest != null) {

                String screenshotPath =
                        "../screenshots/" + fileName;

                currentTest.addScreenCaptureFromPath(
                        screenshotPath,
                        status + " Test Screenshot");
            }

        } catch (IOException | RuntimeException e) {

            System.out.println(
                    "Screenshot Error: " + e.getMessage());
        }
    }

    // Generate HTML Report
    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        System.out.println(
                "Extent Report Generated Successfully!");
    }
}
