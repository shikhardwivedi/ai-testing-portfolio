package com.shikhar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton   = By.id("login-button");
    By errorMessage  = By.cssSelector(".error-message-container h3");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver,
                Duration.ofSeconds(10));
    }

    // Actions
    public void enterUsername(String username) {
        wait.until(ExpectedConditions
                        .visibilityOfElementLocated(usernameField))
                .clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions
                        .visibilityOfElementLocated(passwordField))
                .clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions
                        .elementToBeClickable(loginButton))
                .click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions
                        .visibilityOfElementLocated(errorMessage))
                .getText();
    }

    public void loginWith(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}