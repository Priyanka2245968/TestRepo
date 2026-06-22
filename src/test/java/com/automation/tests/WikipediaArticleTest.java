package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void viewWikipediaArticle() {
        WikipediaArticlePage page = new WikipediaArticlePage(this);
        page.navigateToWikipedia("https://www.wikipedia.org/");
        page.searchWikipedia("Photosynthesis");
        page.clickSearchResult("Photosynthesis");
        page.verifyArticleTitle("Photosynthesis");
        page.takeScreenshot("photosynthesis_article");
    }
}