package com.shikhar.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener
        implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark =
                new ExtentSparkReporter(
                        "target/ExtentReport.html");
        spark.config().setReportName(
                "AI Testing Portfolio");
        spark.config().setDocumentTitle(
                "Shikhar Dubey — SDET");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Shikhar Dubey");
        extent.setSystemInfo("Framework",
                "Selenium + Java + BDD + AI");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Environment", "QA");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(
                result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        // Take screenshot
        try {
            WebDriver driver = ((BaseTest) result
                    .getInstance()).driver;

            if (driver != null) {
                String timestamp =
                        new SimpleDateFormat("yyyyMMdd_HHmmss")
                                .format(new Date());
                String screenshotPath = "target/screenshots/"
                        + result.getName()
                        + "_" + timestamp + ".png";

                // Create screenshots folder
                new File("target/screenshots")
                        .mkdirs();

                // Capture screenshot
                File src = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src,
                        new File(screenshotPath));

                // Attach to report
                test.get().fail("❌ Screenshot:",
                        MediaEntityBuilder
                                .createScreenCaptureFromPath(
                                        screenshotPath)
                                .build());

                System.out.println(
                        "📸 Screenshot saved: "
                                + screenshotPath);
            }
        } catch (IOException e) {
            System.out.println(
                    "Screenshot failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("⚠️ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println(
                "📊 Report: target/ExtentReport.html");
    }
}