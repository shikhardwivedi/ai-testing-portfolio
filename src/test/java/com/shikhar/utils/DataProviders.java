package com.shikhar.utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][] {
                // username, password, shouldPass
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"problem_user", "secret_sauce", true},
                {"standard_user", "wrong_pass", false},
                {"", "", false}
        };
    }

    @DataProvider(name = "checkoutData")
    public static Object[][] checkoutData() {
        return new Object[][] {
                // firstName, lastName, postalCode, shouldPass
                {"Shikhar", "Dubey", "201301", true},
                {"", "Dubey", "201301", false},
                {"Shikhar", "", "201301", false},
                {"Shikhar", "Dubey", "", false}
        };
    }
}