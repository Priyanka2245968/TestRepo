package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewWikipediaArticleTest extends BaseTestManager {

    @Test
    public void viewPhotosynthesisArticle() {
        WikipediaArticlePage page = new WikipediaArticlePage(this);
        page.navigateToWikipedia();
        page.searchForTopic("Photosynthesis");
        page.openArticle("Photosynthesis");
        assertThat(page.page).hasTitle("Photosynthesis - Wikipedia");
        page.takeScreenshot("photosynthesis_article.png");
    }
}