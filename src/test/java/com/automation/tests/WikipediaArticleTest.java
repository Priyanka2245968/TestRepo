package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void verifyPhotosynthesisArticleIsDisplayed() {
        WikipediaArticlePage page = new WikipediaArticlePage(this);
        page.navigateToWikipedia("https://www.wikipedia.org/");
        page.searchForArticle("Photosynthesis");
        page.openArticle("Photosynthesis");
        boolean isArticleDisplayed = page.isArticleDisplayed("Photosynthesis");
        Assert.assertTrue(isArticleDisplayed, "Photosynthesis article is not displayed");
        page.takeScreenshot("photosynthesis-article.png");
    }
}