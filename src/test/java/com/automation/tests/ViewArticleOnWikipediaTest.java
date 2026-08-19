package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testViewArticleOnWikipediaHappyPath() throws Exception {
        System.out.println("✨ Starting 'View Article on Wikipedia' Happy Path Test");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToGoogleHomepage();
        pageObject.verifyGoogleLogoVisible();
        pageObject.verifySearchBoxPresent();
        pageObject.enterSearchTerm("baby doll");
        pageObject.clickSearchButton();
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ 'View Article on Wikipedia' Happy Path Test completed successfully!");
    }

    @Test
    public void testViewArticleOnWikipediaNegativeScenario1() throws Exception {
        System.out.println("✨ Starting 'View Article on Wikipedia' Negative Scenario 1 Test");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToGoogleHomepage();
        pageObject.enterSearchTerm("");
        pageObject.clickSearchButton();
        pageObject.verifyErrorMessageDisplayed();
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ 'View Article on Wikipedia' Negative Scenario 1 Test completed successfully!");
    }
}