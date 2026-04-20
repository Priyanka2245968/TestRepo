package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Click on the search bar");
        page.locator("button[type='submit']").first().click();
    }

    public void step3() throws Exception {
        System.out.println("📍 Enter a random string 'asdfghjk' in the search bar");
        page.locator("#ooui-php-1").first().fill("asdfghjk");
    }

    public void step4() throws Exception {
        System.out.println("📍 Clear the search bar");
        page.locator("#ooui-php-1").first().fill("");
    }

    public void step5() throws Exception {
        System.out.println("📍 Enter a valid topic 'Python (programming language)' in the search bar");
        page.locator("#ooui-php-1").first().fill("Python (programming language)");
    }

    public void step6() throws Exception {
        System.out.println("📍 Press Enter to submit search");
        page.keyboard().press("Enter");
    }

    public void step7() throws Exception {
        System.out.println("📍 Click on the top search result titled 'Python (programming language)'");
        page.locator("button[type='submit']").first().click();
    }

    public void step8() throws Exception {
        System.out.println("📍 Attempt to click on a non-existent link on the page");
        page.locator("button[type='submit']").first().click();
    }

    public void step9() throws Exception {
        System.out.println("📍 Scroll down to a section in the article");
        page.keyboard().press("PageDown");
    }

    public void step10() throws Exception {
        System.out.println("📍 Right-click on the page and select 'View Page Source'");
        page.locator("button[type='submit']").first().click();
    }

    public void step11() throws Exception {
        System.out.println("📍 Close the Page Source window");
        page.locator("button[type='submit']").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}