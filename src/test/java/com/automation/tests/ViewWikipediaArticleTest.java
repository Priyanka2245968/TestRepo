package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.ViewWikipediaArticlePage;

public class ViewWikipediaArticleTest extends BaseTestManager {
    
    @Test
    public void testViewWikipediaArticle() throws Exception {
        System.out.println("✨ Starting View Wikipedia Article Test");
        System.out.println("🌐 Navigating to: https://www.wikipedia.org/");
        
        ViewWikipediaArticlePage pageObject = new ViewWikipediaArticlePage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}