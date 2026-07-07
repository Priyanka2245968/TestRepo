package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testHappyPath_ViewArticleSuccessfully() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.clickSearchButton();
        pageObject.clickArticleLink("Python (programming language)");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("Python (programming language) - Wikipedia");
        pageObject.takeScreenshot("article-page.png");
    }

    @Test
    public void testAccessWithoutAuthentication() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        assertThat(page).hasTitle("Wikipedia");
        pageObject.searchForArticle("Java programming language");
        pageObject.clickSearchButton();
        pageObject.clickArticleLink("Java (programming language)");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("Java (programming language) - Wikipedia");
        pageObject.takeScreenshot("java-article.png");
    }

    @Test
    public void testBlankSearch_NoResults() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("Search results - Wikipedia");
        Locator searchResultsContainer = pageObject.getSearchResultsContainer();
        assertTrue(searchResultsContainer.textContent().contains("Your search did not match any documents."));
        pageObject.takeScreenshot("blank-search.png");
    }
}
