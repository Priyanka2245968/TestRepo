package com.automation.pages;

import com.microsoft.playwright.Page;
import com.automation.base.BaseTestManager;

public class LoginPage {
    private Page page;

    public LoginPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void step1() throws Exception {
        System.out.println("📍 Navigate to the login page");
        page.navigate("https://the-internet.herokuapp.com/login");
    }

    public void step2() throws Exception {
        System.out.println("📍 Enter username in the username field");
        page.locator("#username").first().fill("tomsmith");
    }

    public void step3() throws Exception {
        System.out.println("📍 Enter password in the password field");
        page.locator("#password").first().fill("SuperSecretPassword!");
    }

    public void step4() throws Exception {
        System.out.println("📍 Click the Login button");
        page.locator("//button[contains(normalize-space(.),\"Login\")]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}