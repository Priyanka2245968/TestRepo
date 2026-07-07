package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator pythonArticleLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("//button[contains(normalize-space(.),'Search')]");
        this.pythonArticleLink = page.locator("//a[contains(@href, '/wiki/Python_(programming_language)')]//h3");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForArticle(String term) {
        System.out.println("📍 In the 'Search Wikipedia' field, enter '" + term + "'");
        searchInput.fill(term);
        searchButton.click();
    }

    public void verifySearchResultsLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        org.testng.Assert.assertTrue(page.url().contains("Special:Search"));
    }

    public void clickPythonArticleLink() {
        System.out.println("📍 Click the 'Python (programming language)' link in the search results");
        pythonArticleLink.click();
    }

    public void verifyPythonArticleLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        org.testng.Assert.assertEquals(page.title(), "Python (programming language) - Wikipedia");
    }

    public void verifyArticleStructureAndPresentation() {
        System.out.println("📍 Observe the article structure and presentation");
        org.testng.Assert.assertTrue(page.locator("#toc").isVisible());
        org.testng.Assert.assertTrue(page.locator("#bodyContent").isVisible());
    }

    public void navigateUsingTableOfContents() {
        System.out.println("📍 Navigate using the table of contents");
        page.locator("#toc a").first().click();
    }

    public void clickInternalLinks() {
        System.out.println("📍 Click on internal links related to Python");
        page.locator("#bodyContent a").first().click();
    }

    public void clickSearchWithoutTerm() {
        System.out.println("📍 Click the 'Search' icon without entering any text in the search field");
        searchButton.click();
    }

    public void verifyNoResultsFound() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        org.testng.Assert.assertTrue(page.locator(".mw-search-nonefound").isVisible());
    }

    public void enterVeryLongTextInSearchField() {
        String veryLongText = "This is a very long text that exceeds the maximum allowed length for a search query on Wikipedia. It is intended to trigger an error message when searching with such a long text. This text should be long enough to exceed the maximum allowed length, which is typically around 500 characters or so. We will keep adding more text to make it even longer and ensure that it triggers the expected error message when searching on Wikipedia."; 
        searchInput.fill(veryLongText);
    }

    public void enterInvalidTextInSearchField() {
        String invalidText = "!@#$%^&*()_+"; 
        searchInput.fill(invalidText);
    }

    public void clickSearchIcon() {
        searchButton.click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
