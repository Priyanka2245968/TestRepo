package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewArticleOnWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.clickSearchButton();
        pageObject.clickArticleLink("Python (programming language)");
        assertThat(page).hasTitle("Python (programming language) - Wikipedia");
        pageObject.takeScreenshot("python-article.png");
    }

    @Test
    public void testArticleContentReadableAndSuitableForLearning() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Photosynthesis");
        pageObject.clickSearchButton();
        pageObject.clickArticleLink("Photosynthesis");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(pageObject.getArticleContent()).containsText("Photosynthesis is a process used by plants and other organisms");
        pageObject.takeScreenshot("photosynthesis-article.png");
    }

    @Test
    public void testNoArticleSearchShowsBlankPage() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(pageObject.getNoResultsMessage()).containsText("No results found");
        pageObject.takeScreenshot("no-results.png");
    }
}