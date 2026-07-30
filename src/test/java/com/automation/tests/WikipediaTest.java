package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaTest extends BaseTestManager {

    @Test
    public void testSearchForTopicAndViewArticle() {
        WikipediaPage pageObject = new WikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTopic("HTML");
        pageObject.clickSearchButton();
        pageObject.clickSearchResult();
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("wikipedia-html-article.png");
    }

                    @Test
    public void testWikipediaAccessibleToAnonymousUsers() {
        WikipediaPage pageObject = new WikipediaPage(this);
        pageObject.navigateToWikipedia();
        assertThat(getPage().locator("input[name='search']")).isVisible();
        pageObject.takeScreenshot("wikipedia-homepage.png");
    }

    @Test
    public void testNoArticleInSearchBar() {
        WikipediaPage pageObject = new WikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        assertThat(page).hasTitle("Search - Wikipedia");
        assertThat(page.locator("p.mw-search-nonefound")).containsText("There were no results matching the query.");
        pageObject.takeScreenshot("wikipedia-no-search-results.png");
    }
}