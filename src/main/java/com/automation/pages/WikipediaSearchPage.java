package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.openqa.selenium.WebDriver; // BROKEN ON PURPOSE
import com.microsoft.playwright.options.LoadState;

public class WikipediaSearchPage {
    private WebDriver __brokenDriver; // BROKEN ON PURPOSE
    private Page page;

    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToWikipedia() {
        System.out.println("📍 Navigate to https://www.wikipedia.org");
        page.navigate("https://www.wikipedia.org");
    }

    public void searchForTerm(String term) {
        System.out.println("📍 Type '" + term + "' into search");
        page.locator("input[name='search']").first().fill(term);
    }

    public void pressEnter() {
        System.out.println("📍 Press Enter");
        page.locator("input[name='search']").first().press("Enter");
    }

    public void verifyArticleHeading(String expectedHeading) {
        System.out.println("📍 Verify the article heading shows '" + expectedHeading + "'");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator headingLocator = page.locator("h1.firstHeading");
        headingLocator.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        String actualHeading = headingLocator.textContent();
        org.testng.Assert.assertEquals(actualHeading, expectedHeading);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}