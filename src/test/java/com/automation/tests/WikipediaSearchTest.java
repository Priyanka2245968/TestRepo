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
        
        String screenshotName = "wikipedia-search-" + System.currentTimeMillis() + ".png";
        pageObject.takeScreenshot(screenshotName);
        
        String currentUrl = page.url();
        assert currentUrl.contains("/wiki/Artificial_intelligence") : "URL does not contain expected text after search";
        
        System.out.println("✅ Test completed successfully!");
    }
}