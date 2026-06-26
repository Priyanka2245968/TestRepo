package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @DataProvider(name = "searchTerms")
    public static Object[][] searchTerms() {
        return new Object[][] {
            {"Photosynthesis"},
            {"Quantum mechanics"},
            {"Machine learning"}
        };
    }

    @Test(dataProvider = "searchTerms")
    public void testViewWikipediaArticle(String searchTerm) {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm(searchTerm);
        pageObject.clickTopSearchResult();
        pageObject.verifyArticlePageLoaded();
        pageObject.takeScreenshot(searchTerm + "_article.png");
    }
}