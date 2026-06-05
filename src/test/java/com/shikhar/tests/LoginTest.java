package com.shikhar.tests;

import com.shikhar.pages.LoginPage;
import com.shikhar.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith("standard_user", "secret_sauce");
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "Login failed");
        System.out.println("✅ Valid login passed");
    }

    @Test
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith("standard_user", "wrongpassword");
        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains("Username and password do not match"),
                "Error message not shown");
        System.out.println("✅ Invalid login passed");
    }

    @Test
    public void emptyFieldsTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith("", "");
        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains("Username is required"),
                "Validation not shown");
        System.out.println("✅ Empty fields test passed");
    }
}