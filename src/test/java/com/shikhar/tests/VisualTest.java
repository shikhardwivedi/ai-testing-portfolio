package com.shikhar.tests;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.shikhar.utils.BaseTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class VisualTest extends BaseTest {

    private Eyes eyes;
    private EyesRunner runner;

    @BeforeSuite
    public void visualSetup() {
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey("YOUR_APPLITOOLS_API_KEY");
    }

    @Test
    public void visualLoginPageTest() {
        eyes.open(driver, "OrangeHRM", "Login Page Visual Test");
        driver.get("https://opensource-demo.orangehrmlive.com");
        eyes.checkWindow("Login Page");
        eyes.close();
        System.out.println("✅ Visual test passed");
    }

    @AfterSuite
    public void visualTeardown() {
        runner.getAllTestResults();
    }
}