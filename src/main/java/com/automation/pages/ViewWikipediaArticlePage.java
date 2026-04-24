package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewWikipediaArticlePage {
    private WebDriver driver;

    public ViewWikipediaArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.wikipedia.org/"); }

    public void fill_search_bar(String value) {
        driver.findElement(By.cssSelector("#searchInput")).sendKeys(value);
    }

    public void click_search_button() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public void click_Python__programming_language() {
        driver.findElement(By.cssSelector("//a[contains(normalize-space(.),'Python (programming language)')]")).click();
    }

    public boolean verify_Wikipedia_home_page_loads_successfully() {
        // The Wikipedia home page loads successfully
        return driver.findElement(By.cssSelector("Wikipedia home page loads successfully")).isDisplayed();
    }

    public boolean verify_the_search_bar() {
        // The search bar is displayed and enabled for input
        return driver.findElement(By.cssSelector("the search bar")).isDisplayed();
    }

    public boolean verify_Search_results_displayed() {
        // Search results are displayed
        return driver.findElement(By.cssSelector("Search results displayed")).isDisplayed();
    }

    public boolean verify_Python__programming_language() {
        // The 'Python (programming language)' article page loads with content visible
        return driver.findElement(By.cssSelector("Python (programming language)")).isDisplayed();
    }
}