package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertFalse;

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
        Locator noResultsMessage = pageObject.getNoResultsMessage();
        noResultsMessage.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assertThat(noResultsMessage).isVisible();
    }
}