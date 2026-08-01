package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testViewArticleOnValidTopicSearch() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML element");
        pageObject.clickSearchButton();
        pageObject.viewArticle();
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testSearchWithVeryLongText() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        String longText = "A text longer than 500 characters".repeat(20);
        pageObject.searchForTerm(longText);
        pageObject.clickSearchButton();
        pageObject.viewArticle();
        pageObject.takeScreenshot("wikipedia-long-text.png");
    }

    @Test
    public void testSearchWithInvalidText() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("!@#$%^&*()_+");
        pageObject.clickSearchButton();
        pageObject.viewArticle();
        pageObject.takeScreenshot("wikipedia-invalid-text.png");
    }
}