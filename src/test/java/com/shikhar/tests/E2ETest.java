package com.shikhar.tests;

import com.shikhar.pages.*;
import com.shikhar.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2ETest extends BaseTest {

    @Test
    public void addToCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith(
                "standard_user", "secret_sauce");

        ProductsPage productsPage =
                new ProductsPage(driver);
        Assert.assertTrue(
                productsPage.isOnProductsPage(),
                "Not on products page");

        productsPage.addFirstProductToCart();
        Assert.assertEquals(
                productsPage.getCartCount(), "1",
                "Cart count should be 1");
        System.out.println("✅ Add to cart passed");
    }

    @Test
    public void removeFromCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith(
                "standard_user", "secret_sauce");

        ProductsPage productsPage =
                new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(
                cartPage.isOnCartPage(),
                "Not on cart page");
        Assert.assertEquals(
                cartPage.getCartItemCount(), 1,
                "Cart should have 1 item");

        cartPage.removeFirstItem();
        Assert.assertTrue(
                cartPage.isCartEmpty(),
                "Cart should be empty");
        System.out.println("✅ Remove from cart passed");
    }

    @Test
    public void productSortingTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith(
                "standard_user", "secret_sauce");

        ProductsPage productsPage =
                new ProductsPage(driver);
        productsPage.sortBy("Name (Z to A)");

        String firstProduct =
                productsPage.getFirstProductName();
        Assert.assertTrue(
                firstProduct.startsWith("T"),
                "Sorting Z to A failed");
        System.out.println("✅ Product sorting passed");
    }

    @Test
    public void completeCheckoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWith(
                "standard_user", "secret_sauce");

        ProductsPage productsPage =
                new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.addProductToCartByIndex(1);
        Assert.assertEquals(
                productsPage.getCartCount(), "2",
                "Cart should have 2 items");

        productsPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(
                cartPage.isOnCartPage());
        cartPage.clickCheckout();

        CheckoutPage checkoutPage =
                new CheckoutPage(driver);
        checkoutPage.enterDetails(
                "Shikhar", "Dubey", "201301");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(
                checkoutPage.isOrderComplete(),
                "Order not completed");
        System.out.println("✅ E2E checkout passed");
    }
}