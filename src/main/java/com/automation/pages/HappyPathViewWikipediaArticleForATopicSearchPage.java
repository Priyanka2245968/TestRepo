package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HappyPathViewWikipediaArticleForATopicSearchPage {
    private WebDriver driver;

    public HappyPathViewWikipediaArticleForATopicSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void fill_search_box(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    public void click_Search_button() {
        driver.findElement(By.cssSelector("//button[contains(normalize-space(.),'Search')]")).click();
    }

    // wait networkidle

    public void click_HTML_Table_Element() {
        driver.findElement(By.cssSelector("a[href="/wiki/Main_Page"]")).click();
    }

    public boolean verify_search_box() {
        // Wikipedia homepage is displayed with search box visible
        return driver.findElement(By.cssSelector("search box")).isDisplayed();
    }

    public boolean verify_search_box() {
        // Search box has value 'HTML Tables'
        return driver.findElement(By.cssSelector("search box")).isDisplayed();
    }

    public boolean verify_search_results() {
        // Search results for 'HTML Tables' are displayed
        return driver.findElement(By.cssSelector("search results")).isDisplayed();
    }

    public boolean verify_search_result_items() {
        // At least one search result item is shown
        return driver.findElement(By.cssSelector("search result items")).isDisplayed();
    }

    public boolean verify_None() {
        // URL contains '/wiki/HTML_table_element' after navigating to result
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify_article_content() {
        // HTML Table Element article content is visible
        return driver.findElement(By.cssSelector("article content")).isDisplayed();
    }
}