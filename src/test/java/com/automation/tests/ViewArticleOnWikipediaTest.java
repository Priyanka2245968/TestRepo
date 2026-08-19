package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testViewArticleOnWikipedia() {
        System.out.println("✨ Starting View Article on Wikipedia Test");
        System.out.println("🌐 Navigating to: https://www.wikipedia.org");

        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);

        pageObject.navigateToWikipedia();
        pageObject.enterSearchTerm("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.verifyArticlePageLoaded();
        pageObject.verifyArticleContentVisible();
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}