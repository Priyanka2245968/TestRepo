package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaPage {
    private Page page;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void step1(String description) {
        System.out.println("\ud83d\udccd " + description);
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2(String description) {
        System.out.println("\ud83d\udccd " + description);
        Locator searchField = page.locator("input[name='search']");
        searchField.fill("Python programming language");
        searchField.press("Enter");
    }

    public void step3(String description) {
        System.out.println("\ud83d\udccd " + description);
        Locator articleLink = page.locator("a:has-text(\"Python (programming language)\")")
            .waitFor(new Locator.WaitForOptions().setTimeout(10000));
        articleLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void step4(String description) {
        System.out.println("\ud83d\udccd " + description);
        assertThat(page).hasTitle("Python (programming language) - Wikipedia");
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("article_python.png")));
    }

    public void step5(String description) {
        System.out.println("\ud83d\udccd " + description);
    }
}