package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.BOK21Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BOK21Test extends BaseTestManager {

    @Test
    public void testErrorMessageForVeryLongSearchText() {
        BOK21Page pageObject = new BOK21Page(this);
        pageObject.navigateToWikipedia();
        pageObject.enterVeryLongTextInSearchField();
        pageObject.clickSearchButton();
        pageObject.waitForErrorMessage();
        assertTrue(pageObject.getErrorMessage().contains("The search query is too long"), "Error message for long search text is incorrect");
        pageObject.takeScreenshot("BOK21-TC04.png");
    }

    @Test
    public void testNoResultsMessageForInvalidSearchText() {
        BOK21Page pageObject = new BOK21Page(this);
        pageObject.navigateToWikipedia();
        pageObject.enterInvalidTextInSearchField();
        pageObject.clickSearchButton();
        pageObject.waitForNoResultsMessage();
        assertTrue(pageObject.getNoResultsMessage().contains("No results found"), "No results message is incorrect");
        pageObject.takeScreenshot("BOK21-TC05.png");
    }
}
