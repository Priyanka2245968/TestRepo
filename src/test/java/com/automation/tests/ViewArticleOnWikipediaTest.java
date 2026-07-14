package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPath_ViewWikipediaArticle() {
        WikipediaSearchPage wikipediaPage = new WikipediaSearchPage(this);
        wikipediaPage.navigateToWikipedia();
        wikipediaPage.searchForTopic("Python programming language");
        wikipediaPage.waitForSearchResults();
        wikipediaPage.clickSearchResult("Python (programming language)");
        wikipediaPage.waitForArticleLoad();
        assertThat(wikipediaPage.getArticleTitleLocator()).containsText("Python (programming language)");
        wikipediaPage.takeScreenshot("python-article.png");
    }

    @Test
    public void testNegative_BlankSearchInput() {
        WikipediaSearchPage wikipediaPage = new WikipediaSearchPage(this);
        wikipediaPage.navigateToWikipedia();
        wikipediaPage.searchForTopic(" ");
        wikipediaPage.waitForSearchResults();
        String noResultsMessage = wikipediaPage.getNoResultsMessage();
        Assert.assertTrue(noResultsMessage.contains("No results found. Please try different search terms."));
        wikipediaPage.takeScreenshot("blank-search.png");
    }

    @Test
    public void testNegative_OverlengthSearchInput() {
        WikipediaSearchPage wikipediaPage = new WikipediaSearchPage(this);
        wikipediaPage.navigateToWikipedia();
        String longText = "a".repeat(501);
        wikipediaPage.searchForTopic(longText);
        wikipediaPage.waitForSearchResults();
        String errorMessage = wikipediaPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("An error has occurred while searching: Search request is longer than the maximum allowed length"));
        wikipediaPage.takeScreenshot("overlength-search.png");
    }

    @Test
    public void testNegativeInvalidSearchInput() {
        WikipediaSearchPage pageObject = new WikipediaSearchPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTopic("!@#$%^&*()");
        pageObject.waitForSearchResults();
        String title = page.title();
        Assert.assertTrue(title.contains("!@#$%^&*() - Search results - Wikipedia"));
        String searchInput = pageObject.getSearchInputValue();
        Assert.assertEquals(searchInput, "!@#$%^&*()");
        String errorMessage = pageObject.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("There were no results matching the query"));
        pageObject.takeScreenshot("invalid_search_result.png");
    }
}