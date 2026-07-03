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
        pageObject.searchForPythonProgrammingLanguage();
        pageObject.verifySearchResultsHeading();
        pageObject.clickPythonProgrammingLanguageLink();
        pageObject.verifyArticlePageLoaded();
        pageObject.takeScreenshot("wikipedia-python-article.png");
    }

    @Test
    public void testAnonymousAccessToWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        assertThat(page).hasURL("https://www.wikipedia.org/");
        assertThat(pageObject.getLoginLink()).isVisible();
        assertThat(pageObject.getCreateAccountLink()).isVisible();
        pageObject.clickCreateAccountLink();
        assertThat(pageObject.getCreateAccountPageHeading()).isVisible();
        pageObject.takeScreenshot("wikipedia-create-account.png");
    }

    @Test
    public void testNoSearchQueryProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(pageObject.getNoSearchResultsContainer()).isVisible();
    }
}