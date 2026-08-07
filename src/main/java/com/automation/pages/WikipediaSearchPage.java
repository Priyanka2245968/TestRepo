package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WikipediaSearchPage {
    private Page page;

    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToWikipedia() {
        System.out.println("📍 Navigate to https://www.wikipedia.org");
        page.navigate("https://www.wikipedia.org");
    }

    public void searchForTerm(String term) {
        System.out.println("📍 Type '" + term + "' into the search input");
        page.locator("input[name='search']").first().fill(term);
    }

    public void pressEnterToSearch() {
        System.out.println("📍 Press Enter to search");
        page.locator("input[name='search']").first().press("Enter");
    }

    public void verifyArticleHeading(String expectedHeading) {
        System.out.println("📍 Verify the article page shows the heading '" + expectedHeading + "'");
        page.locator("h1.firstHeading").first().waitFor().assertTextContains(expectedHeading);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.options.ScreenshotOptions().setPath(filename));
    }
}