package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ViewArticlePage {
    private final Page page;
    private static final String SEARCH_INPUT_SELECTOR = "input[name='search']"; 
    private static final String SEARCH_BUTTON_SELECTOR = "button[type='submit']";  
    private static final String ARTICLE_LINK_SELECTOR = ".mw-search-results a";
    private static final String ERROR_MESSAGE_SELECTOR = ".mw-search-nonefound";
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleLink;
    private final Locator errorMessage;

    public ViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator(SEARCH_INPUT_SELECTOR);
        this.searchButton = page.locator(SEARCH_BUTTON_SELECTOR);
        this.articleLink = page.locator(ARTICLE_LINK_SELECTOR);
        this.errorMessage = page.locator(ERROR_MESSAGE_SELECTOR);
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickArticleLink(String linkText) {
        Locator articleLink = this.articleLink.filter(new Locator.FilterOptions().setHasText(linkText)).first();
        articleLink.click();
        assertTrue(page.title().contains(linkText), "Article link did not open the expected article");
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(filename));
    }
}