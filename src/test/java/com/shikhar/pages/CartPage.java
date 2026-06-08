package com.shikhar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = ".cart_item")
    List<WebElement> cartItems;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(css = ".btn_secondary")
    WebElement removeButton;

    @FindBy(css = ".cart_item_label")
    List<WebElement> cartItemNames;

    @FindBy(className = "title")
    WebElement pageTitle;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isOnCartPage() {
        wait.until(ExpectedConditions
                .visibilityOf(pageTitle));
        return pageTitle.getText()
                .equals("Your Cart");
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions
                .elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }

    public void removeFirstItem() {
        wait.until(ExpectedConditions
                .elementToBeClickable(removeButton));
        removeButton.click();
    }

    public boolean isCartEmpty() {
        return cartItems.size() == 0;
    }
}