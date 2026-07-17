package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test(description = "BOK-21-TC-01: Happy Path - View Wikipedia article for a valid search term")
    public void testViewWikipediaArticleForValidSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.clickFirstSearchResult();
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test(description = "BOK-21-TC-02: Additional Acceptance Criterion - Wikipedia website is accessible anonymously")
    public void testWikipediaAccessibleAnonymously() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        assertThat(page).hasTitle("Wikipedia");
        pageObject.takeScreenshot("wikipedia-homepage.png");
    }

    @Test(description = "BOK-21-TC-03: Negative - When no article is provided in the search bar it should show a blank page with no search result")
    public void testNoSearchTermShowsBlankPage() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("No results found - Wikipedia");
        pageObject.takeScreenshot("no-search-results.png");
    }

    @Test
    public void testNegativeWhenVeryLongTextIsProvidedInSearchBar() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.enterTextInSearchField("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat...");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String errorMessage = pageObject.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("An error has occurred while searching: Search request is longer than the maximum allowed length"));
        pageObject.takeScreenshot("negative-very-long-text-" + System.currentTimeMillis() + ".png");
    }

    @Test
    public void testNegativeWhenInvalidTextIsProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.enterTextInSearchField("asdfjkl;");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String noResultsMessage = pageObject.getNoResultsMessage();
        Assert.assertTrue(noResultsMessage.contains("There were no results matching the query"));
        assertThat(pageObject.getSearchResultLinks()).isVisible().not();
        pageObject.takeScreenshot("negative-invalid-text-" + System.currentTimeMillis() + ".png");
    }
}