package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.JavaTutorialsSearchPage;

public class JavaTutorialsSearchTest extends BaseTestManager {
    
    @Test
    public void testJavaTutorialsSearch() throws Exception {
        System.out.println("✨ Starting Java Tutorials Search Test");
        System.out.println("🌐 Navigating to: https://www.geeksforgeeks.org/");
        
        JavaTutorialsSearchPage pageObject = new JavaTutorialsSearchPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        pageObject.step5();
        pageObject.step6();
        pageObject.step7();
        pageObject.step8();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}