package com.shikhar.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

// AI Generated Test
// User Story: As a user I want to login to SauceDemo so that I can see products

public class AIGeneratedLoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void validLoginTest() {
        wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.id("user-name")))
            .sendKeys("standard_user");
        driver.findElement(By.id("password"))
            .sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void invalidLoginTest() {
        wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.id("user-name")))
            .sendKeys("standard_user");
        driver.findElement(By.id("password"))
            .sendKeys("wrongpass");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(
            driver.findElement(By.cssSelector(".error-message-container h3"))
            .isDisplayed());
    }

    @Test
    public void emptyLoginTest() {
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(
            driver.findElement(By.cssSelector(".error-message-container h3"))
            .isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
