package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;

    public ViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void verifySearchResultsLoaded() {
        assertThat(page.locator(".mw-search-results")).isVisible();
    }

    public void clickArticleLink(String articleName) {
        Locator articleLink = page.locator("a[href='/wiki/" + articleName + "']");
        articleLink.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.ATTACHED));
        articleLink.click();
    }

    public void verifyArticleLoaded(String articleName) {
        assertThat(page.locator("#firstHeading")).containsText(articleName);
    }

    public void verifyNoSearchResults() {
        assertThat(page.locator(".mw-search-nonefound")).isVisible();
    }
}
