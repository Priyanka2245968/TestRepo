package com.automation.standalone;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

/**
 * Standalone Playwright Test: Sample Login Test
 * 
 * This is a simple standalone test using Playwright Java directly.
 * No framework dependencies - just pure Playwright automation.
 */
public class SampleLoginTestStandalone {
    public static void main(String[] args) {
        System.out.println("✨ Starting Sample Login Test (Standalone)");

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );

            BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setViewportSize(1280, 720)
            );

            Page page = context.newPage();

            System.out.println("🌐 Navigating to: https://www.google.com");
            page.navigate("https://www.google.com");

            System.out.println("📍 verify Google logo is visible");
            page.locator("img[alt*=\"Google\"], img[alt*=\"logo\"]").first()
                .waitFor(new Locator.WaitForOptions().setTimeout(5000));

            System.out.println("📍 verify search box is present");
            Locator searchBox = page.locator("textarea[name=\"q\"]").first();
            searchBox.waitFor(new Locator.WaitForOptions().setTimeout(5000));

            // Extra: clear any existing text
            searchBox.fill("");

            System.out.println("📍 focus and enter \"baby doll\" in the search box");
            searchBox.click();
            page.keyboard().type("baby doll", new Keyboard.TypeOptions().setDelay(150));

            // Wait for the value to actually appear
            for (int i = 0; i < 10; i++) {
                if (searchBox.inputValue().equals("baby doll")) break;
                page.waitForTimeout(300);
            }
            assert searchBox.inputValue().equals("baby doll") : "Text not entered!";

            System.out.println("📍 press Enter to search");
            page.keyboard().press("Enter");

            String screenshotPath = "standalone-screenshot-" + System.currentTimeMillis() + ".png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
            System.out.println("📸 Screenshot saved: " + screenshotPath);

            System.out.println("✅ Test completed successfully!");
            System.out.println("⏳ Keeping browser open for 10 seconds...");
            page.waitForTimeout(10000);

            browser.close();
        }
    }
}