package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleTest extends BaseTestManager {

    @Test(description = "Happy Path — Search for and View a Wikipedia Article")
    public void testViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("Photosynthesis");
        pageObject.viewArticle();
        assertThat(page).hasTitle("Photosynthesis - Wikipedia");
        pageObject.takeScreenshot("photosynthesis_article.png");
    }
}