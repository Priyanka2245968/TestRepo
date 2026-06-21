package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void viewWikipediaArticle() {
        String url = "https://www.wikipedia.org/";
        String searchQuery = "HTML Tutorial";
        String expectedArticleTitle = "HTML Tutorial - Wikipedia";

        testManager.getPage().navigate(url);
        System.out.println("📍 Navigated to " + url);

        testManager.getPage().locator("#searchInput").first().fill(searchQuery);
        System.out.println("📍 Entered search query: " + searchQuery);

        testManager.getPage().locator("#searchButton").first().click();
        System.out.println("📍 Clicked search button");

        testManager.getPage().locator(".mw-search-results").first().waitFor();
        System.out.println("📍 Verified search results page is loaded");

        testManager.getPage().locator(".mw-search-results li a").first().click();
        System.out.println("📍 Clicked first search result link");

        WikipediaArticlePage articlePage = new WikipediaArticlePage(testManager);
        articlePage.verifyArticlePageLoaded(expectedArticleTitle);
        System.out.println("📍 Verified Wikipedia article page is loaded");

        articlePage.takeScreenshot("wikipedia_article");
        System.out.println("📍 Captured screenshot of Wikipedia article page");
    }
}