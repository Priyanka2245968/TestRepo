package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void enterLongSearchInput() {
        searchInput.fill("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam...");
    }

    public void enterInvalidSearchInput() {
        searchInput.fill("jl234kd9fq3#$%^");
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String getErrorMessage() {
        return page.locator("text=An error has occurred while searching: Search request is longer than the maximum allowed length").textContent();
    }

    public String getNoResultsMessage() {
        return page.locator(".mw-search-nonefound").textContent();
    }
}