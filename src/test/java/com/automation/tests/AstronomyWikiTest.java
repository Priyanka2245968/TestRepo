package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.AstronomyWikiPage;

public class AstronomyWikiTest extends BaseTestManager {
    
    @Test
    public void testAstronomyWiki() throws Exception {
        System.out.println("✨ Starting Astronomy Wikipedia Test");
        System.out.println("🌐 Navigating to: https://www.wikipedia.org/");
        
        AstronomyWikiPage pageObject = new AstronomyWikiPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}