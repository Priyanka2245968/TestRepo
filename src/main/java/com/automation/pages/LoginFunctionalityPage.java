package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFunctionalityPage {
    private WebDriver driver;

    public LoginFunctionalityPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://the-internet.herokuapp.com/login"); }

    public void fill_username_field(String value) {
        driver.findElement(By.cssSelector("#username")).sendKeys(value);
    }

    public void fill_password_field(String value) {
        driver.findElement(By.cssSelector("#password")).sendKeys(value);
    }

    public void click_login_button() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public boolean verify_None() {
        // URL should change to include '/secure' after login
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify___flash_message() {
        // A success flash message should be displayed
        return driver.findElement(By.cssSelector("__flash_message__")).isDisplayed();
    }

    public boolean verify_Logout() {
        // Logout button or link should be visible
        return driver.findElement(By.cssSelector("Logout")).isDisplayed();
    }
}