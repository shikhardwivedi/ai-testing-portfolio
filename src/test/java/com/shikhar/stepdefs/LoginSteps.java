package com.shikhar.stepdefs;

import com.shikhar.pages.LoginPage;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @When("user enters username {string} and password {string}")
    public void userEntersCredentials(
            String username, String password) {
        loginPage.loginWith(username, password);
    }

    @Then("user should see the inventory page")
    public void userShouldSeeInventoryPage() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "Inventory page not loaded");
        driver.quit();
    }

    @Then("error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        Assert.assertTrue(
                loginPage.getErrorMessage().length() > 0,
                "Error message not displayed");
        driver.quit();
    }
}