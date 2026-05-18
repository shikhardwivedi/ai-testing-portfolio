package com.shikhar.tests;

import com.shikhar.pages.LoginPage;
import com.shikhar.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith("Admin", "admin123");
        Assert.assertTrue(driver.getCurrentUrl()
                        .contains("dashboard"),
                "Login failed — dashboard not loaded");
        System.out.println("✅ Valid login passed");
    }

    @Test
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith("Admin", "wrongpassword");
        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Invalid credentials");
        System.out.println("✅ Invalid login test passed");
    }

    @Test
    public void emptyFieldsTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith("", "");
        Assert.assertTrue(
                driver.getCurrentUrl().contains("login"),
                "Should stay on login page");
        System.out.println("✅ Empty fields test passed");
    }
}