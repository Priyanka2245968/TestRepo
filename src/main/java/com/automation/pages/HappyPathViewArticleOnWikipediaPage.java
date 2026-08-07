package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HappyPathViewArticleOnWikipediaPage {
    private WebDriver driver;

    public HappyPathViewArticleOnWikipediaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void fill_search_box(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    public void click_Search() {
        driver.findElement(By.cssSelector("//button[contains(normalize-space(.),'Search')]")).click();
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void click_Photosynthesis() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public boolean verify_Search_Wikipedia() {
        // The Wikipedia homepage is displayed with the 'Search Wikipedia' field visible
        return driver.findElement(By.cssSelector("Search Wikipedia")).isDisplayed();
    }

    public boolean verify_Photosynthesis() {
        // The text 'Photosynthesis' is entered in the search field
        return driver.findElement(By.cssSelector("Photosynthesis")).isDisplayed();
    }

    public boolean verify_a_list_of_search_results() {
        // A list of search results is displayed
        return driver.findElement(By.cssSelector("a list of search results")).isDisplayed();
    }

    public boolean verify_None() {
        // The search results page shows a heading 'Search results for: Photosynthesis'
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify_None() {
        // The 'Photosynthesis' article page loads with the title visible and article content displayed
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }
}