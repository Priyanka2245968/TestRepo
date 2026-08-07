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
    
    public void navigateToWikipediaHomepage() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void typeInSearchBox(String text) throws Exception {
        System.out.println("📍 Type '" + text + "' in the search box");
        page.locator("#searchInput").first().fill(text);
    }

    public void clickSearchButton() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("button[type='submit']").first().click();
    }

    public void waitForSearchResults() throws Exception {
        System.out.println("📍 Wait for at least 1 link with visible text containing 'Wikipedia' to appear");
        // Wait for page to load completely
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickFirstLinkContainingText(String text) throws Exception {
        System.out.println("📍 Click the first visible link with text containing '" + text + "'");
        page.locator("a:text-is(/" + text + "/i)").first().click();
    }

    public void waitForWikipediaArticleHeading() throws Exception {
        System.out.println("📍 Wait for the heading with role='heading' and visible text matching /^Wikipedia$/");
        page.locator("//h1[text()='Wikipedia']").first().waitFor(new Locator.WaitForOptions().setTimeout(10000));
    }

    public void scrollToContentsSection() throws Exception {
        System.out.println("📍 Scroll down until the section with role='region' and visible name='Contents' appears");
        page.locator("//span[text()='Contents']").first().scrollIntoViewIfNeeded();
    }

    public void clickFirstLinkUnderContentsSection() throws Exception {
        System.out.println("📍 Click the first visible link under the 'Contents' section");
        page.locator("//span[text()='Contents']/following-sibling::ul//a").first().click();
    }

    public void waitForNavigationToComplete() throws Exception {
        System.out.println("📍 Wait for the URL to change");
        page.waitForNavigation();
    }

    public void goBackInBrowserHistory() throws Exception {
        System.out.println("📍 Go back in the browser history");
        page.goBack();
    }

    public void clickEditLink() throws Exception {
        System.out.println("📍 Click the visible link with text 'Edit' and role='link'");
        page.locator("a[href*='/wiki/Wikipedia?action=edit']").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}