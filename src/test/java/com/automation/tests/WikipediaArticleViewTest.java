package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;

public class WikipediaArticleViewTest extends BaseTestManager {
    
    @Test
    public void testViewWikipediaArticle() throws Exception {
        System.out.println("✨ Starting Wikipedia Article View Test");
        System.out.println("🌐 Navigating to: https://www.wikipedia.org");
        
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        pageObject.step5();
        pageObject.step6();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}