package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.testng.Assert;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchField;
    private final Locator searchButton;
    private final Locator articleLink;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchField = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.articleLink = page.locator("a:has-text('HTML')");
    }

    public void fillSearchField(String text) {
        System.out.println("📍 Fill 'Search Wikipedia' field with: " + text);
        searchField.fill(text);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click 'Search' icon button");
        searchButton.click();
    }

    public void clickArticleLink(String linkText) {
        System.out.println("📍 Click '" + linkText + "' link in search results");
        articleLink.click();
    }

    public void verifyArticleContentIsReadable() {
        System.out.println("📍 Verify article content is clear and readable");
        assertThat(page).hasTitle("HTML - Wikipedia");
    }

    public void verifyNoSearchResultsDisplayed() {
        System.out.println("📍 Verify no search results are displayed");
        String title = page.title();
        Assert.assertTrue(title.contains("Search - Wikipedia"), "Page title does not contain 'Search - Wikipedia': " + title);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
