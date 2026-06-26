package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

public class ViewWikipediaArticleTest extends BaseTestManager {

    @Test(description = "BOK-21-UI-TC-01: Happy Path — Search for and view a Wikipedia article")
    public void testViewWikipediaArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.openArticle("Python (programming language)");
        pageObject.verifyArticleLoaded("Python (programming language) - Wikipedia");
        pageObject.takeScreenshot("wikipedia_article.png");
    }
}