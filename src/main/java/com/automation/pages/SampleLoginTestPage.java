package com.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.automation.base.BaseTestManager;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SampleLoginTestPage {
    private final Page page;
    
    public SampleLoginTestPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() {
        System.out.println("📍 Navigate to https://www.google.com");
        page.navigate("https://www.google.com");
    }

    public void step2() {
        System.out.println("📍 navigate to Google homepage");
        page.navigate("https://www.google.com");
    }

    public void step3() {
        System.out.println("📍 verify Google logo is visible");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page.locator("//img[@alt='Google'], //img[contains(@src, 'logo')], //img[contains(@alt, 'logo')]")
                .first()).isVisible();
    }

    public void step4() {
        System.out.println("📍 verify search box is present");
        assertThat(page.locator("textarea[name=\"q\"], input[name=\"q\"]").first()).isVisible();
    }

    public void step5() {
        System.out.println("📍 enter \"baby doll\" in the search box");
        page.locator("textarea[name=\"q\"], input[name=\"q\"]").first().fill("baby doll");
    }

    public void step6() {
        System.out.println("📍 click on the search box");
        page.locator("input[type=\"submit\"][name=\"btnK\"], button[type=\"submit\"]").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}