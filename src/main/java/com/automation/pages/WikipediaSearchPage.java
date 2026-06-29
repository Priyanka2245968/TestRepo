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
    private final Locator firstSearchResult = page.locator("div.mw-search-results li.mw-search-result:first-child a");

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
        searchButton.click();
    }

    public void verifySearchResultsLoaded() {
        assertThat(page).hasURL("https://en.wikipedia.org/wiki/Special:Search");
        assertThat(page.locator("div.mw-search-results")).isVisible();
    }

    public void clickFirstSearchResult() {
        firstSearchResult.click();
    }

    public void verifyArticlePageLoaded(String articleTitle) {
        assertThat(page).hasURL("https://en.wikipedia.org/wiki/" + articleTitle);
        assertThat(page.locator("h1#firstHeading")).containsText(articleTitle);
    }
}