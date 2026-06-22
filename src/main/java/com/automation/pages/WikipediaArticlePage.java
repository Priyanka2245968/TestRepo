package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.automation.utils.WaitUtils.waitForElementToBeVisible;

public class WikipediaArticlePage {
    private static final Logger logger = LogManager.getLogger(WikipediaArticlePage.class);
    private final BaseTestManager testManager;
    private final Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.page = testManager.getPage();
    }

    public void navigateToWikipedia(String url) {
        logger.info("Navigating to " + url);
        page.navigate(url);
    }

    public void searchForArticle(String query) {
        logger.info("Searching for '" + query + "'");
        waitForElementToBeVisible(page.locator("#searchInput")).fill(query);
        waitForElementToBeVisible(page.locator("#searchButton")).click();
    }

    public void openArticle(String articleTitle) {
        logger.info("Opening article: " + articleTitle);
        waitForElementToBeVisible(page.locator("//a[contains(@title, '" + articleTitle + "')]")).first().click();
    }

    public boolean isArticleDisplayed(String articleTitle) {
        logger.info("Verifying article: " + articleTitle);
        return waitForElementToBeVisible(page.locator("//h1[contains(., '" + articleTitle + "')]")).isVisible();
    }

    public void takeScreenshot(String filename) {
        logger.info("Taking screenshot: " + filename);
        page.screenshot(new com.microsoft.playwright.options.ScreenshotOptions().setPath(com.microsoft.playwright.impl