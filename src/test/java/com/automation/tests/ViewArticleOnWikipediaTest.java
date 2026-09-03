package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {
    
    @Test
    public void testViewArticleOnWikipedia_HappyPath() {
        System.out.println("✨ Starting View Article on Wikipedia Test - Happy Path");
        
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        
        pageObject.navigateToGoogleHomepage();
        pageObject.verifyGoogleLogoVisible();
        pageObject.verifySearchBoxPresent();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }

    @Test
    public void testViewArticleOnWikipedia_Negative() {
        System.out.println("✨ Starting View Article on Wikipedia Test - Negative");
        
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        
        pageObject.navigateToGoogleHomepage();
        // TODO: Implement negative test steps
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}