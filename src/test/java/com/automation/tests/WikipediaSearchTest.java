package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;

public class WikipediaSearchTest extends BaseTestManager {
    
    @Test
    public void testWikipediaSearch() throws Exception {
        System.out.println("✨ Starting Wikipedia Search Test");
        
        WikipediaSearchPage pageObject = new WikipediaSearchPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        pageObject.step5();
        pageObject.step6();
        pageObject.step7();
        pageObject.step8();
        pageObject.step9();
        pageObject.step10();
        pageObject.step11();
        
        pageObject.takeScreenshot("wikipedia-search-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}