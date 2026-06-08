package com.shikhar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(css = ".complete-header")
    WebElement confirmationMessage;

    @FindBy(className = "title")
    WebElement pageTitle;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterDetails(String firstName,
                             String lastName, String postalCode) {
        wait.until(ExpectedConditions
                .visibilityOf(firstNameField));
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions
                .elementToBeClickable(finishButton));
        finishButton.click();
    }

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions
                .visibilityOf(confirmationMessage));
        return confirmationMessage.getText();
    }

    public boolean isOrderComplete() {
        return getConfirmationMessage()
                .contains("Thank you for your order");
    }
}