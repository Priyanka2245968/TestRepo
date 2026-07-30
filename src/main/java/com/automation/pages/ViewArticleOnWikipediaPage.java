package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;

import java.nio.file.Paths;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    public final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.articleLink = page.locator("a[href='/wiki/Main_Page']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void fillSearchField(String text) {
        System.out.println("📍 Fill 'Search Wikipedia' field with: " + text);
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click 'Search' icon button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickArticleLink() {
        System.out.println("📍 Click link for the 'HTML' article in the search results");
        articleLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyErrorMessageForLongSearch() {
        String errorMessage = page.locator("//div[@class='error']").textContent();
        Assert.assertTrue(errorMessage.contains("An error has occurred while searching: Search request is longer than the maximum allowed length"));
    }

    public void verifyErrorMessageForInvalidSearch() {
        String errorMessage = page.locator("//div[@class='error']").textContent();
        Assert.assertTrue(errorMessage.contains("There were no results matching the query"));
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}
