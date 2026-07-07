package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPath_SearchAndViewArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.clickSearchIcon();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("Python (programming language)");
        assertThat(pageObject.getArticleTitle()).contains("Python (programming language)");
        pageObject.takeScreenshot("python_article.png");
    }

    @Test
    public void testAdditionalCriterion_ArticleContentForLearning() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Photosynthesis");
        pageObject.clickSearchIcon();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("Photosynthesis");
        assertThat(pageObject.getArticleContent()).contains("Photosynthesis is a process used by plants and other organisms");
        pageObject.takeScreenshot("photosynthesis_article.png");
    }

    @Test
    public void testNegative_NoSearchTermShowsBlankPage() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchIcon();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(pageObject.getSearchResultsContainer()).isVisible();
        assertThat(pageObject.getSearchResultsContainer()).hasText("No results found");
        pageObject.takeScreenshot("no_search_results.png");
    }
}