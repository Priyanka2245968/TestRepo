package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaSearchPage {
    private Page page;

    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    private final Locator searchInput = page.locator("#searchInput");
    private final Locator searchButton = page.locator("button[type='submit']");
    private final Locator firstSearchResult = page.locator("a[href='/wiki/Main_Page']");

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
    }

    public void clickFirstSearchResult() {
        firstSearchResult.click();
    }

    public void verifyArticlePageLoaded(String articleTitle) {
        assertThat(page).hasURL("https://en.wikipedia.org/wiki/" + articleTitle);
    }
}