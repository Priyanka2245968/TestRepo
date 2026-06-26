package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private Page page;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void step1(String description) {
        System.out.println("📍 " + description);
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2(String description) {
        System.out.println("📍 " + description);
        page.locator("input[name='search']").first().fill("Python programming language");
    }

    public void step3(String description) {
        System.out.println("📍 " + description);
        page.locator("button[type='submit']").first().click();
    }

    public void step4(String description) {
        System.out.println("📍 " + description);
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("a:has-text('Python (programming language)')").first().click();
    }

    public void step5(String description) {
        System.out.println("📍 " + description);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(filename + ".png"));
    }
}