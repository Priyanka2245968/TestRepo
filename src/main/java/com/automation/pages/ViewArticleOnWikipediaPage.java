package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator pythonArticleLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.pythonArticleLink = page.locator("#vector-main-menu-dropdown-checkbox").first();
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForArticle(String term) {
        System.out.println("\ud83d\udccd In the 'Search Wikipedia' field, enter '" + term + "'");
        searchInput.fill(term);
        searchButton.click();
    }

    public void verifySearchResultsLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasURL(".*Special:Search.*");
    }

    public void verifyNoSearchResultsLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).not().hasURL(".*Special:Search.*");
    }

    public void clickPythonArticleLink() {
        System.out.println("\ud83d\udccd Clicking on the 'Python (programming language)' link");
        pythonArticleLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyPythonArticleLoaded() {
        assertThat(page).hasURL(".*Python_(programming_language).*");
    }

    public void verifyArticleStructureAndPresentation() {
        // Add assertions to verify article structure and presentation
    }

    public void navigateUsingTableOfContents() {
        // Add code to navigate using table of contents
        // Add assertions to verify navigation
    }

    public void clickInternalLinks() {
        // Add code to click internal links
        // Add assertions to verify internal link navigation
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
}