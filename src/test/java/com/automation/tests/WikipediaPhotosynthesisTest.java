package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaPhotosynthesisPage;

public class WikipediaPhotosynthesisTest extends BaseTestManager {
    
    @Test
    public void testViewPhotosynthesisArticle() throws Exception {
        System.out.println("✨ Starting Wikipedia Photosynthesis Article Test");
        
        WikipediaPhotosynthesisPage pageObject = new WikipediaPhotosynthesisPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        pageObject.step5();
        
        pageObject.takeScreenshot("wikipedia-photosynthesis-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}