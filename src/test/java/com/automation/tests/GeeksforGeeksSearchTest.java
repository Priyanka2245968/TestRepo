package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.GeeksforGeeksSearchPage;

public class GeeksforGeeksSearchTest extends BaseTestManager {
    
    @Test
    public void testGeeksforGeeksSearch() throws Exception {
        System.out.println("✨ Starting GeeksforGeeks Search Test");
        
        GeeksforGeeksSearchPage pageObject = new GeeksforGeeksSearchPage(this);
        
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