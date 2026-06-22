package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticlePage {
    private Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTopic(String topic) {
        page.locator("input[name='search']").first().fill(topic);
        page.locator("button[type='submit']").first().click();
    }

    public void openArticle(String articleTitle) {
        page.locator("//a[text()='" + articleTitle + "']").first().click();
    }

    public void verifyArticleDisplayed(String articleTitle) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator articleTitleLocator = page.locator("//h1[contains(@class, 'firstHeading')]");
        assertThat(articleTitleLocator).containsText(articleTitle);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}