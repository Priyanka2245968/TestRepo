package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.PreconditionPage;

public class PreconditionTest extends BaseTestManager {
    
    @Test
    public void testPrecondition() {
        System.out.println("✨ Starting Precondition Test");
        System.out.println("🌐 Navigating to: https://www.w3schools.com");
        
        PreconditionPage pageObject = new PreconditionPage(this);
        
        page.navigate("https://www.w3schools.com");
        System.out.println("🌐 Navigating to: https://www.w3schools.com");
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}