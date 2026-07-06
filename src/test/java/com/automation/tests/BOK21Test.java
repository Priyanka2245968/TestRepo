package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.BOK21Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class BOK21Test extends BaseTestManager {

    @Test
    public void testNegativeWhenVeryLongTextIsProvided() {
        BOK21Page pageObject = new BOK21Page(this);
        pageObject.navigateToWikipedia();
        pageObject.enterVeryLongTextInSearchField();
        pageObject.clickSearchButton();
        pageObject.waitForErrorMessage();
        pageObject.verifyErrorMessageForLongText();
        pageObject.takeScreenshot("BOK21-TC04.png");
    }

    @Test
    public void testNegativeWhenInvalidTextIsProvided() {
        BOK21Page pageObject = new BOK21Page(this);
        pageObject.navigateToWikipedia();
        pageObject.enterInvalidTextInSearchField();
        pageObject.clickSearchButton();
        pageObject.waitForNoResultsMessage();
        pageObject.verifyNoResultsMessage();
        pageObject.takeScreenshot("BOK21-TC05.png");
    }
}
