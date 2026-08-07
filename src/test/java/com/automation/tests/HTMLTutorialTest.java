package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialPage;

public class HTMLTutorialTest extends BaseTestManager {
    
    @Test
    public void testViewHTMLTutorial() throws Exception {
        System.out.println("✨ Starting View HTML Tutorial Article on Wikipedia");
        System.out.println("🌐 Navigating to: https://www.wikipedia.org/");
        
        HTMLTutorialPage pageObject = new HTMLTutorialPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}