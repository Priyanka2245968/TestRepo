package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFunctionalityPage {
    private WebDriver driver;

    public LoginFunctionalityPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://the-internet.herokuapp.com"); }

    public void fill_Username_field(String value) {
        driver.findElement(By.cssSelector("#username")).sendKeys(value);
    }

    public void fill_password_input(String value) {
        driver.findElement(By.cssSelector("#password")).sendKeys(value);
    }

    public void click_Login_button() {
        driver.findElement(By.cssSelector("//button[contains(normalize-space(.),'Login')]")).click();
    }

    public boolean verify_None() {
        // User should be redirected to the secure area
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify_A_success_flash_message_should_be_displayed() {
        // A success flash message should be displayed
        return driver.findElement(By.cssSelector("A success flash message should be displayed")).isDisplayed();
    }

    public boolean verify_Logout_button() {
        // A Logout button should be visible
        return driver.findElement(By.cssSelector("Logout button")).isDisplayed();
    }
}