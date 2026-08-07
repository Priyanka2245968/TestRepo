package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HappyPathSearchAndViewWikipediaArticlePage {
    private WebDriver driver;

    public HappyPathSearchAndViewWikipediaArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void fill_search_box(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    public void click_magnifier___search_icon_button() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public void click_Python__programming_language() {
        driver.findElement(By.cssSelector("//a[contains(normalize-space(.),'Python (programming language)')]")).click();
    }

    public void click_Python__programming_language() {
        driver.findElement(By.cssSelector("//a[contains(normalize-space(.),'Python (programming language)')]")).click();
    }

    public void click_History() {
        driver.findElement(By.cssSelector("//a[contains(normalize-space(.),'History')]")).click();
    }

    public boolean verify_Search_Wikipedia() {
        // The Wikipedia homepage is displayed with the 'Search Wikipedia' field prominently visible
        return driver.findElement(By.cssSelector("Search Wikipedia")).isDisplayed();
    }

    public boolean verify_search_suggestions_displayed_user_types() {
        // The search suggestions are displayed as the user types
        return driver.findElement(By.cssSelector("search suggestions displayed user types")).isDisplayed();
    }

    public boolean verify_Python_programming_language() {
        // The search results page shows with a list of article links matching 'Python programming language'
        return driver.findElement(By.cssSelector("Python programming language")).isDisplayed();
    }

    public boolean verify_None() {
        // The 'Python (programming language)' article page loads with the article title and introduction visible
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify_History() {
        // The article content including sections like 'History' and 'Applications' is displayed
        return driver.findElement(By.cssSelector("History")).isDisplayed();
    }

    public boolean verify_History() {
        // The page scrolls down to the 'History' section of the article content
        return driver.findElement(By.cssSelector("History")).isDisplayed();
    }
}