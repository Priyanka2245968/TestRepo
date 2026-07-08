package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPath_ViewWikipediaArticleForASearchedTopic() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.waitForSearchResults();
        pageObject.clickHtmlLinkInSearchResults();
        pageObject.waitForArticlePageLoad();
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("html-article.png");
    }

    @Test
    public void testAdditionalAcceptanceCriterion_WikipediaAccessibleWithoutLogin() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        assertThat(pageObject.getSearchField()).isVisible();
        pageObject.takeScreenshot("wikipedia-homepage.png");
    }

    @Test
    public void testNegative_BlankSearchReturnsNoResults() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm(" ");
        pageObject.waitForSearchResults();
        assertThat(pageObject.getNoResultsMessage()).isVisible();
    }
}