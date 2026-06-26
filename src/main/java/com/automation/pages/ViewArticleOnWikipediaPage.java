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
        Locator searchField = page.locator("input[name='search']");
        searchField.fill("Python programming language");
        searchField.press("Enter");
    }

    public void step3(String description) {
        System.out.println("📍 " + description);
        Locator articleLink = page.locator("a:has-text(\"Python (programming language)\")");
        articleLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void step4(String description) {
        System.out.println("📍 " + description);
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("article_python.png")));
    }

    public void step5(String description) {
        System.out.println("📍 " + description);
    }
}