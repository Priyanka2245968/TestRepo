package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void viewWikipediaArticle() {
        String url = "https://www.wikipedia.org/";
        String searchQuery = "HTML Tutorial";
        String expectedArticleTitle = "HTML Tutorial - Wikipedia";

        testManager.getPage().navigate(url);
        System.out.println("\ud83d\udccd Navigated to " + url);

        WikipediaArticlePage articlePage = new WikipediaArticlePage(testManager);
        articlePage.enterSearchQuery(searchQuery);
        System.out.println("\ud83d\udccd Entered search query: " + searchQuery);

        articlePage.clickSearchButton();
        System.out.println("\ud83d\udccd Clicked search button");

        testManager.getPage().locator(".mw-search-results").first().waitFor();
        System.out.println("\ud83d\udccd Verified search results page is loaded");

        articlePage.clickFirstSearchResult();
        System.out.println("\ud83d\udccd Clicked first search result link");

        articlePage.verifyArticlePageLoaded(expectedArticleTitle);
        System.out.println("\ud83d\udccd Verified Wikipedia article page is loaded");

        Assert.assertTrue(testManager.getPage().url().contains(expectedArticleTitle.replace(" ", "_")), "Article URL does not match expected title");

        articlePage.takeScreenshot("wikipedia_article");
    }
}