package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchField;
    private final Locator searchButton;
    private final Locator articleLink;
    private final Locator articleContent;
    private final Locator noResultsMessage;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchField = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.articleLink = page.locator("a:has-text('%s')");
        this.articleContent = page.locator(".mw-parser-output");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
    }

    public void fillSearchField(String text) {
        System.out.println("\ud83d\udccd Fill 'Search Wikipedia' field with: " + text);
        searchField.fill(text);
    }

    public void clickSearchButton() {
        System.out.println("\ud83d\udccd Click 'Search' icon button");
        searchButton.click();
    }

    public void clickArticleLink(String linkText) {
        System.out.println("\ud83d\udccd Click '" + linkText + "' link in search results");
        articleLink.fill(linkText).click();
    }

    public boolean verifyArticleContentIsReadable() {
        System.out.println("\ud83d\udccd Verify article content is clear and readable");
        return articleContent.waitFor().isVisible();
    }

    public boolean verifyNoResultsDisplayed() {
        System.out.println("\ud83d\udccd Verify no results message is displayed");
        return noResultsMessage.waitFor().isVisible();
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new com.microsoft.playwright.options.ScreenshotOptions().setPath(fileName));
    }
}