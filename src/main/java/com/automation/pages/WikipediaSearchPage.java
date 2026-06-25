package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaSearchPage {
    private final Page page;

    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    private final Locator searchInput = page.locator("input[name='search']");
    private final Locator searchButton = page.locator("button[type='submit']");
    private final Locator htmlLink = page.locator("a[href='/wiki/HTML']");
    private final Locator searchResults = page.locator(".mw-search-results");
    private final Locator noSearchResults = page.locator(".mw-search-nonefound");

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
        searchButton.click();
    }

    public void verifySearchResults(String term) {
        assertThat(page).hasTitle(term + " - Search results - Wikipedia");
        assertThat(searchResults).isVisible();
    }

    public void verifyNoSearchResults() {
        assertThat(noSearchResults).isVisible();
    }

    public void clickHtmlLink() {
        htmlLink.click();
    }

    public void verifyHtmlArticle() {
        assertThat(page).hasTitle("HTML - Wikipedia");
    }
}