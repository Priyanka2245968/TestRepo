package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.SampleLoginTestPage;

public class SampleLoginTestTest extends BaseTestManager {
    
    @Test
    public void testSampleLoginTest() {
        System.out.println("✨ Starting Sample Login Test");
        System.out.println("🌐 Navigating to: https://www.google.com");
        
        SampleLoginTestPage pageObject = new SampleLoginTestPage(this);
        
        page.navigate("https://www.google.com");
        System.out.println("🌐 Navigating to: https://www.google.com");
        
        pageObject.step1();
        pageObject.step2();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}
