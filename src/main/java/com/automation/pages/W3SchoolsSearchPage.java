package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class W3SchoolsSearchPage {
    private Page page;

    public W3SchoolsSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToW3Schools() {
        System.out.println("📍 Navigate to W3Schools website");
        page.navigate("https://www.w3schools.com");
    }

    public void clickSearchBox() {
        System.out.println("📍 Click on the search input box at the top");
        page.locator("#search2").first().click();
    }

    public void enterSearchQuery(String query) {
        System.out.println("📍 Enter \"" + query + "\" in the search box");
        page.locator("#search2").first().fill(query);
    }

    public void waitForSearchResults() {
        System.out.println("📍 Wait for search results to load");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifySearchResults(String expectedText) {
        System.out.println("📍 Verify search results contain: " + expectedText);
        Locator resultLocator = page.locator("text=" + expectedText);
        resultLocator.waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }
}