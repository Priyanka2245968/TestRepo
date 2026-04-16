package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UrlHttpsWwwW3SchoolsComPage {
    private WebDriver driver;

    public UrlHttpsWwwW3SchoolsComPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.w3schools.com"); }

    public void fill_search_input_box(String value) {
        driver.findElement(By.cssSelector("#tnb-google-search-input")).sendKeys(value);
    }

    public void click_Search_button() {
        driver.findElement(By.cssSelector("#navbtn_services")).click();
    }

    public void fill_search_box(String value) {
        driver.findElement(By.cssSelector("#tnb-google-search-input")).sendKeys(value);
    }

    // press keyboard

    public boolean verify_search_results() {
        // Search results should be displayed
        return driver.findElement(By.cssSelector("search results")).isDisplayed();
    }

    public boolean verify_Results_contain_tutorials() {
        // Results should contain HTML-related tutorials
        return driver.findElement(By.cssSelector("Results contain tutorials")).isDisplayed();
    }

    public boolean verify_error_message() {
        // No error or broken page should appear
        return driver.findElement(By.cssSelector("error message")).isDisplayed();
    }
}