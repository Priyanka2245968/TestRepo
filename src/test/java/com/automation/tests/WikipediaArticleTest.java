package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void searchAndViewWikipediaArticle_HappyPath_ArticleDisplayed() {
        WikipediaArticlePage page = new WikipediaArticlePage(this);
        page.navigateToWikipedia("https://www.wikipedia.org/");
        page.searchWikipedia("HTML");
        page.clickSearchResult("HTML");
        page.takeScreenshot("wikipedia_article_html.png");
    }

    @Test
    public void searchWikipedia_InvalidInput_BlankSearchField_ErrorMessageDisplayed() {
        WikipediaArticlePage page = new WikipediaArticlePage(this);
        page.navigateToWikipedia("https://www.wikipedia.org/");
        page.searchWikipedia("");
        page.verifyErrorMessage("Please enter a valid search term");
        page.takeScreenshot("wikipedia_blank_search_error.png");
    }

    @Test
    public void searchWikipedia_BoundaryCase_MaximumSearchLength_ErrorMessageDisplayed() {
        WikipediaArticlePage page = new WikipediaArticlePage(this);
        String longSearchTerm = "a".repeat(500);
        page.navigateToWikipedia("https://www.wikipedia.org/");
        page.searchWikipedia(longSearchTerm);
        page.verifyErrorMessage("No results found for " + longSearchTerm + ". Try different keywords.");
        page.takeScreenshot("wikipedia_long_search_error.png");
    }
}