package com.shikhar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "button[data-test*='add-to-cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public boolean isOnProductsPage() {
        wait.until(ExpectedConditions
                .visibilityOf(pageTitle));
        return pageTitle.getText()
                .equals("Products");
    }

    public void addFirstProductToCart() {
        wait.until(ExpectedConditions
                .visibilityOfAllElements(addToCartButtons));
        wait.until(ExpectedConditions
                .elementToBeClickable(
                        addToCartButtons.get(0)));
        addToCartButtons.get(0).click();
    }

    public void addProductToCartByIndex(int index) {
        wait.until(ExpectedConditions
                .visibilityOfAllElements(addToCartButtons));
        wait.until(ExpectedConditions
                .elementToBeClickable(
                        addToCartButtons.get(index)));
        addToCartButtons.get(index).click();
    }

    public String getCartCount() {
        try {
            wait.until(ExpectedConditions
                    .visibilityOf(cartBadge));
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void goToCart() {
        wait.until(ExpectedConditions
                .elementToBeClickable(cartIcon));
        cartIcon.click();
    }

    public void sortBy(String option) {
        wait.until(ExpectedConditions
                .visibilityOf(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option);
    }

    public String getFirstProductName() {
        wait.until(ExpectedConditions
                .visibilityOfAllElements(productNames));
        return productNames.get(0).getText();
    }

    public int getProductCount() {
        return productNames.size();
    }
}