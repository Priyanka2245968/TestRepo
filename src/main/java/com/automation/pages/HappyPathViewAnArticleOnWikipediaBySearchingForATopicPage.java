package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HappyPathViewAnArticleOnWikipediaBySearchingForATopicPage {
    private WebDriver driver;

    public HappyPathViewAnArticleOnWikipediaBySearchingForATopicPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void fill_search_box(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    public void click_Search_button() {
        driver.findElement(By.cssSelector("//button[contains(normalize-space(.),'Search')]")).click();
    }

    public void click_HTML_Tutorial_link() {
        driver.findElement(By.cssSelector("a[href='/w/index.php?title=HTML_Tutorial&action=edit&redlink=1']")).click();
    }

    // screenshot 

    public boolean verify_search_box() {
        // The Wikipedia home page is displayed with the 'Search Wikipedia' field visible
        return driver.findElement(By.cssSelector("search box")).isDisplayed();
    }

    public boolean verify_search_box() {
        // The entered text 'HTML Tutorial' appears in the search field
        return driver.findElement(By.cssSelector("search box")).isDisplayed();
    }

    public boolean verify_Search_results_for__HTML_Tutorial() {
        // The search results page displays with the heading 'Search results for: HTML Tutorial'
        return driver.findElement(By.cssSelector("Search results for: HTML Tutorial")).isDisplayed();
    }

    public boolean verify_None() {
        // The 'HTML Tutorial' page loads with the article content
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify_HTML_Tutorial() {
        // The page heading 'HTML Tutorial' is displayed and the article content is readable
        return driver.findElement(By.cssSelector("HTML Tutorial")).isDisplayed();
    }
}