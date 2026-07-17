package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

                                @Test(description = "BOK-21-TC-01: Happy Path - View Wikipedia article for a valid search term")
    public void testViewWikipediaArticleForValidSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.clickSearchButton();
        pageObject.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.clickFirstSearchResult();
        pageObject.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(getPage()).hasTitle("HTML - Wikipedia");
        String articleContent = pageObject.getArticleContent();
        org.testng.Assert.assertTrue(articleContent.contains("HTML"));
        pageObject.takeScreenshot("wikipedia-article.png");
    }
}