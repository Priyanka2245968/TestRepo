package com.automation.pages;

import com.automation.base.BaseTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WikipediaArticlePage {
    private final WebDriver driver;

    public WikipediaArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToWikipedia(String url) {
        driver.get(url);
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(By.name("search")));
    }

    public void searchWikipedia(String query) {
        driver.findElement(By.name("search")).sendKeys(query);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public void clickSearchResult(String title) {
        driver.findElement(By.xpath("//a[text()='" + title + "']")).click();
    }

    public void verifyArticleTitle(String title) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), title));
    }

    public void takeScreenshot(String filename) {
        // Implement screenshot logic
    }
}