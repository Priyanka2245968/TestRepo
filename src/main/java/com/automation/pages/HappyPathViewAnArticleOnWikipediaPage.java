package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HappyPathViewAnArticleOnWikipediaPage {
    private WebDriver driver;

    public HappyPathViewAnArticleOnWikipediaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void fill_search_box(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    // press keyboard

    public void click_Photosynthesis() {
        driver.findElement(By.cssSelector("a[href='/wiki/Photosynthesis_(disambiguation)']")).click();
    }

    public void click_Photosynthesis_link() {
        driver.findElement(By.cssSelector("a[href='/wiki/Photosynthesis']")).click();
    }

    public boolean verify_search_box() {
        // Wikipedia homepage is displayed with search box visible
        return driver.findElement(By.cssSelector("search box")).isDisplayed();
    }

    public boolean verify_search_suggestions() {
        // Suggestions appear as user types in search field
        return driver.findElement(By.cssSelector("search suggestions")).isDisplayed();
    }

    public boolean verify_None() {
        // URL contains '/wiki/Photosynthesis' after clicking search result
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify_None() {
        // Page title contains 'Photosynthesis'
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify_Photosynthesis_is_a_process_used_by_plants_and_other_organisms() {
        // Article page contains text about photosynthesis
        return driver.findElement(By.cssSelector("Photosynthesis is a process used by plants and other organisms")).isDisplayed();
    }
}