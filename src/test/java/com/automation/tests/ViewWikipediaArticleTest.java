package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewWikipediaArticleTest extends BaseTestManager {

    @Test
    public void testHappyPathViewWikipediaArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("Python programming language");
        Locator searchResultLink = pageObject.getSearchResultLink("Python (programming language)");
        searchResultLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("Python (programming language) - Wikipedia");
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testNegativeBlankSearchField() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchButton.click();
        assertThat(pageObject.searchInput).hasText("");
        assertThat(page).hasTitle("Wikipedia");
    }

    @Test
    public void testBoundaryLongSearchStringTruncation() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        String longSearchTerm = "a".repeat(257);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm(longSearchTerm);
        assertTrue(pageObject.searchInput.textContent().equals(longSearchTerm));
        String currentUrl = page.url();
        assertTrue(currentUrl.contains(longSearchTerm.substring(0, 256)));
    }
}