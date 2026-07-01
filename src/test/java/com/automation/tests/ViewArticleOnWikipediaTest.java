package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewWikipediaArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTopic("Photosynthesis");
        pageObject.clickSearchButton();
        pageObject.clickPhotosynthesisLink();
        assertThat(getPage()).hasTitle("Photosynthesis - Wikipedia");
        pageObject.takeScreenshot("photosynthesis-article.png");
    }

    @Test
    public void testNegativeSearchFieldEmpty() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.clickSearchButton();
        assertThat(pageObject.getSearchErrorMessage()).containsText("Please enter a search term");
        pageObject.takeScreenshot("search-empty-error.png");
    }

    @Test
    public void testBoundaryLongSearchQuery() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
   