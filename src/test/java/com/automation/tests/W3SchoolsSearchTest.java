package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.W3SchoolsSearchPage;

public class W3SchoolsSearchTest extends BaseTestManager {
    
    @Test
    public void testW3SchoolsSearch() {
        System.out.println("✨ Starting W3Schools Search Test");
        System.out.println("🌐 Navigating to: https://www.w3schools.com");
        
        W3SchoolsSearchPage pageObject = new W3SchoolsSearchPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}