package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        page.navigate("https://en.wikipedia.org/wiki/Main_Page");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.searchBox.fill("Automation");
        pageObject.searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.firstSearchResult.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String articleText = pageObject.articleContent.textContent();
        assertTrue(articleText.contains("Automation"));
    }
}    @Test
    public void testHappyPathSuccessfulWikipediaArticleSearchAndView() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("HTML");
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("html-article.png");
    }

    @Test
    public void testArticleContentIsClearAndSuitableForLearning() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python (programming language)");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("Python (programming language)");
        assertThat(page).hasTitle("Python (programming language) - Wikipedia");
    }
}    @Test
    public void testViewArticleOnWikipedia() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToArticle("Java_(programming_language)");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String articleTitle = pageObject.getArticleTitle();
        Assert.assertTrue(articleTitle.contains("Java (programming language)"));
    }Test
public void testViewArticleOnWikipedia() {
    BaseTestManager testManager = new BaseTestManager();
    testManager.getPage().navigate("https://en.wikipedia.org/wiki/Main_Page");
    testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    WikipediaArticlePage articlePage = new WikipediaArticlePage(testManager);
    articlePage.searchForArticle("Automation");
    articlePage.verifyArticleTitle("Automation");
}