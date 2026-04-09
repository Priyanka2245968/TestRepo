package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WikipediaSearchFunctionalityPage {
    private WebDriver driver;

    public WikipediaSearchFunctionalityPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org"); }

    public void fill_search_field(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    public void click_search_button() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public boolean verify_Wikipedia_homepage_load_successfully() {
        // Wikipedia homepage should load successfully
        return driver.findElement(By.cssSelector("Wikipedia homepage load successfully")).isDisplayed();
    }

    public boolean verify_search_input_field() {
        // Search input field should be visible and clickable
        return driver.findElement(By.cssSelector("search input field")).isDisplayed();
    }

    public boolean verify_the_search_term() {
        // The search term should appear in the input field
        return driver.findElement(By.cssSelector("the search term")).isDisplayed();
    }

    public boolean verify_Clicking_search_navigate_search_results() {
        // Clicking search should navigate to the search results page
        return driver.findElement(By.cssSelector("Clicking search navigate search results")).isDisplayed();
    }
}