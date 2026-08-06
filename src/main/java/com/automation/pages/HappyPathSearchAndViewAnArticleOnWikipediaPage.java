package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HappyPathSearchAndViewAnArticleOnWikipediaPage {
    private WebDriver driver;

    public HappyPathSearchAndViewAnArticleOnWikipediaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void fill_search_box(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    public void click_Wikipedia_search_button() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public void click_HTML_article_link() {
        driver.findElement(By.cssSelector("a[href='/wiki/Main_Page']")).click();
    }

    public boolean verify_search_box() {
        // The Wikipedia homepage is displayed with the 'Search Wikipedia' field visible
        return driver.findElement(By.cssSelector("search box")).isDisplayed();
    }

    public boolean verify_search_box() {
        // The text 'HTML' is entered in the 'Search Wikipedia' field
        return driver.findElement(By.cssSelector("search box")).isDisplayed();
    }

    public boolean verify_search_results() {
        // The search results page is displayed with a list of articles related to 'HTML'
        return driver.findElement(By.cssSelector("search results")).isDisplayed();
    }

    public boolean verify_Search_results_for__HTML() {
        // The search results page displays a heading 'Search results for: HTML'
        return driver.findElement(By.cssSelector("Search results for: HTML")).isDisplayed();
    }

    public boolean verify_HTML_article_content() {
        // The 'HTML' article page is displayed with the content visible and readable
        return driver.findElement(By.cssSelector("HTML article content")).isDisplayed();
    }
}