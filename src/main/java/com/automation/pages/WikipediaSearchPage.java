package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Open Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Verify search input box is visible");
        page.locator("button[type='submit']").first().isVisible();
    }

    public void step3() throws Exception {
        System.out.println("📍 Click on the search input box");
        page.locator("button[type='submit']").first().click();
    }

    public void step4() throws Exception {
        System.out.println("📍 Type 'Artificial Intelligence' in the search box");
        page.locator("#ooui-php-1").first().fill("Artificial Intelligence");
    }

    public void step5() throws Exception {
        System.out.println("📍 Press Enter key to submit search");
        page.keyboard().press("Enter");
    }

    public void step6() throws Exception {
        System.out.println("📍 Wait for search results page to load");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void step7() throws Exception {
        System.out.println("📍 Click the first article link 'Artificial Intelligence - Wikipedia'");
        page.locator("button[type='submit']").first().click();
    }

    public void step8() throws Exception {
        System.out.println("📍 Wait for article page to load");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void step9() throws Exception {
        System.out.println("📍 Verify page heading 'Artificial Intelligence' is visible");
        page.locator("a:has-text('Artificial Intelligence\"i')").first().click();
    }

    public void step10() throws Exception {
        System.out.println("📍 Scroll down to verify article content sections are rendered");
        page.evaluate("window.scrollBy(0, document.body.scrollHeight)");
    }

    public void step11() throws Exception {
        System.out.println("📍 Click on an internal hyperlink within the article, e.g. 'Machine Learning'");
        page.locator("a:has-text('Machine Learning\"i')").first().click();
    }

    public void step12() throws Exception {
        System.out.println("📍 Wait for the linked article page to load");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void step13() throws Exception {
        System.out.println("📍 Verify the new article page heading matches the clicked link");
        page.locator("a:has-text('Machine Learning\"i')").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}