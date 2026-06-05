package com.shikhar.tests;

import com.shikhar.utils.ExtentReportListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@Listeners(ExtentReportListener.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "com.shikhar.stepdefs",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {}