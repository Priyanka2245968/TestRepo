package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialPage;

public class HTMLTutorialTest extends BaseTestManager {
    
    @Test
    public void testHTMLTutorial() throws Exception {
        System.out.println("✨ Starting HTML Tutorial Test");
        
        HTMLTutorialPage pageObject = new HTMLTutorialPage(this);
        
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
        pageObject.step12();
        
        System.out.println("✅ Test completed successfully!");
    }
}