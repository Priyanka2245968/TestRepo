package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaSearchTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        WikipediaSearchPage pageObject = new WikipediaSearchPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.clickSearchButton();
        getPage().waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
        assertThat(getPage().locator("a[href='/wiki/HTML']").first()).isVisible();
        getPage().locator("a[href='/wiki/HTML']").first().click();
        getPage().waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
        assertThat(getPage()).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("wikipedia-search-result.png");
    }

    @Test
    public void testAccessWithoutLogin() {
        WikipediaSearchPage pageObject = new WikipediaSearchPage(this);
        pageObject.navigateToWikipedia();
        assertThat(getPage()).hasTitle("Wikipedia");
        pageObject.takeScreenshot("wikipedia-homepage.png");
    }

    @Test
    public void testBlankSearchShowsNoResults() {
        WikipediaSearchPage pageObject = new WikipediaSearchPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        getPage().waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
        assertThat(getPage().locator("text=There were no results matching the query")).isVisible();
        pageObject.takeScreenshot("wikipedia-blank-search.png");
    }
}