package com.shikhar.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.util.HashMap;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(
            @Optional("chrome") String browser) {

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);

        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments(
                    "--disable-save-password-bubble");
            options.addArguments(
                    "--disable-notifications");
            options.addArguments("--no-first-run");
            options.addArguments(
                    "--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments(
                    "--remote-allow-origins=*");

            HashMap<String, Object> prefs =
                    new HashMap<>();
            prefs.put("credentials_enable_service",
                    false);
            prefs.put(
                    "profile.password_manager_enabled",
                    false);
            prefs.put(
                    "profile.password_manager_leak_detection",
                    false);
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption(
                    "excludeSwitches",
                    new String[]{"enable-automation"});

            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}