package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HappyPathSearchForAndViewAWikipediaArticlePage {
    private WebDriver driver;

    public HappyPathSearchForAndViewAWikipediaArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void fill_search_box(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    // press keyboard

    public void click_Python__programming_language() {
        driver.findElement(By.cssSelector("a[href='/wiki/Category:Python_(programming_language)']")).click();
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public boolean verify_Search_Wikipedia() {
        // The Wikipedia homepage is displayed with the 'Search Wikipedia' field visible
        return driver.findElement(By.cssSelector("Search Wikipedia")).isDisplayed();
    }

    public boolean verify_Search_Wikipedia() {
        // Search suggestions populate as the user types in the 'Search Wikipedia' field
        return driver.findElement(By.cssSelector("Search Wikipedia")).isDisplayed();
    }

    public boolean verify_Python__programming_language____Wikipedia() {
        // The 'Python (programming language) - Wikipedia' search results page is displayed
        return driver.findElement(By.cssSelector("Python (programming language) - Wikipedia")).isDisplayed();
    }

    public boolean verify_Python__programming_language() {
        // The 'Python (programming language)' article page opens
        return driver.findElement(By.cssSelector("Python (programming language)")).isDisplayed();
    }

    public boolean verify_None() {
        // The page heading 'Python (programming language)' is displayed along with the article content
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }
}