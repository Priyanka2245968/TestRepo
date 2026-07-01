package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticlePage {
    private static final Logger logger = LoggerFactory.getLogger(WikipediaArticlePage.class);
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void openArticle(String articleTitle) {
        Locator articleLink = page.locator("#vector-main-menu-dropdown-checkbox").first();
        articleLink.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        articleLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticleOpened(String articleTitle) {
        assertThat(page).hasURL("*" + articleTitle.replace(" ", "_") + "*");
    }

    public void takeScreenshot(String filename) {
        try {
            page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        } catch (Exception e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
        }
    }
}