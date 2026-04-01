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
        page.locator("#nav_search_form input[title='Search W3Schools']").first().click();
    }

    public void enterSearchQuery(String query) {
        System.out.println("📍 Enter search query: " + query);
        page.locator("#nav_search_form input[title='Search W3Schools']").first().fill(query);
    }

    public void submitSearch() {
        System.out.println("📍 Submit search");
        page.locator("#nav_search_form button[type='submit']").first().click();
    }

    public void verifySearchResults() {
        System.out.println("📍 Verify search results are displayed");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator resultsLocator = page.locator("#main .w3-panel");
        resultsLocator.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        assert resultsLocator.count() > 0 : "No search results found!";
    }
}