package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;

public class WikipediaArticleViewTest extends BaseTestManager {

    @Test
    public void testViewArticle() throws Exception {
        System.out.println("✨ Starting Wikipedia Article View Test");

        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);

        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        pageObject.step5();

        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}