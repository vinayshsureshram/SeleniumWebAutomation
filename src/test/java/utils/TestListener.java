package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.io.ByteArrayInputStream;

/**
 * TestNG Listener to capture screenshots on failure and attach to Allure reports.
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.driver;
        if (driver != null) {
            String testName = result.getMethod().getMethodName();
            byte[] screenshotBytes = ScreenshotUtils.captureScreenshot(driver, testName);

            // ✅ Attach screenshot to Allure report
            if (screenshotBytes != null) {
                Allure.addAttachment("Screenshot on Failure - " + testName,
                        new ByteArrayInputStream(screenshotBytes));
            }
        }
        Allure.addAttachment("DOM - ", BaseTest.recordScreenDOM());
    }

    // Optional: log start and end of tests for clarity
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 STARTING TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ PASSED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ SKIPPED TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("===== Test Suite Started =====");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("===== Test Suite Finished =====");
    }
}