package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.W3SchoolsSearchPage;
import org.testng.annotations.Test;

public class W3SchoolsSearchTest extends BaseTestManager {

    @Test
    public void testW3SchoolsSearch() {
        System.out.println("✨ Starting W3Schools Search Test");

        W3SchoolsSearchPage w3SchoolsPage = new W3SchoolsSearchPage(this);

        w3SchoolsPage.navigateToW3Schools();
        w3SchoolsPage.clickSearchBox();
        w3SchoolsPage.enterSearchQuery("HTML tutorials");
        w3SchoolsPage.submitSearch();
        w3SchoolsPage.verifySearchResults();

        System.out.println("✅ W3Schools Search Test completed successfully!");
    }
}