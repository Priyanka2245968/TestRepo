package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.W3SchoolsSearchPage;
import org.testng.annotations.Test;

public class W3SchoolsSearchTest extends BaseTestManager {
    @Test
    public void testW3SchoolsSearch() {
        System.out.println("✨ Starting W3Schools Search Test");

        W3SchoolsSearchPage searchPage = new W3SchoolsSearchPage(this);

        searchPage.navigateToW3Schools();
        searchPage.clickSearchBox();
        searchPage.enterSearchQuery("HTML tutorials");
        searchPage.waitForSearchResults();
        searchPage.verifySearchResults("HTML Tutorial");

        System.out.println("✅ Test completed successfully!");
    }
}