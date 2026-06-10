package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleSearchPage;

public class WikipediaArticleSearchTest extends BaseTestManager {
    
    @Test
    public void testWikipediaArticleSearch() throws Exception {
        System.out.println("✨ Starting Wikipedia Article Search Test");
        
        WikipediaArticleSearchPage pageObject = new WikipediaArticleSearchPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}