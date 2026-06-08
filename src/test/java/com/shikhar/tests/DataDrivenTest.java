package com.shikhar.tests;

import com.shikhar.pages.LoginPage;
import com.shikhar.pages.ProductsPage;
import com.shikhar.utils.BaseTest;
import com.shikhar.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTest extends BaseTest {

    @Test(dataProvider = "loginData",
            dataProviderClass = DataProviders.class)
    public void dataDriverLoginTest(
            String username,
            String password,
            boolean shouldPass) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith(username, password);

        if (shouldPass) {
            ProductsPage productsPage =
                    new ProductsPage(driver);
            Assert.assertTrue(
                    productsPage.isOnProductsPage(),
                    "Login failed for user: " + username);
            System.out.println("✅ Login passed for: "
                    + username);
        } else {
            Assert.assertTrue(
                    driver.getCurrentUrl()
                            .contains("saucedemo.com"),
                    "Should stay on login page for: "
                            + username);
            System.out.println("✅ Login correctly " +
                    "blocked for: " + username);
        }
    }
}