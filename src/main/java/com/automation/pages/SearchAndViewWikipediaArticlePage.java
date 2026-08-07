package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchAndViewWikipediaArticlePage {
    private WebDriver driver;

    public SearchAndViewWikipediaArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void fill_search_bar(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    // press keyboard

    public void click_Python__programming_language() {
        driver.findElement(By.cssSelector("role=link[name="Python (programming language)"i]")).click();
    }

    public boolean verify_Wikipedia_home_page_loads_successfully() {
        // The Wikipedia home page loads successfully
        return driver.findElement(By.cssSelector("Wikipedia home page loads successfully")).isDisplayed();
    }

    public boolean verify_the_search_input_field() {
        // The search input field is visible and enabled
        return driver.findElement(By.cssSelector("the search input field")).isDisplayed();
    }

    public boolean verify_Search_results_displayed_page() {
        // Search results are displayed on a new page
        return driver.findElement(By.cssSelector("Search results displayed page")).isDisplayed();
    }

    public boolean verify_Python__programming_language() {
        // The 'Python (programming language)' article content is displayed
        return driver.findElement(By.cssSelector("Python (programming language)")).isDisplayed();
    }
}