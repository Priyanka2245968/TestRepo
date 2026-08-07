package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    public LoginPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToLoginPage() {
        System.out.println("📍 Navigate to the login page");
        page.navigate("https://the-internet.herokuapp.com/login");
    }

    public void enterUsername(String username) {
        System.out.println("📍 Enter username in the username field");
        page.locator("input#username").first().fill(username);
    }

    public void enterPassword(String password) {
        System.out.println("📍 Enter password in the password field");
        page.locator("input#password").first().fill(password);
    }

    public void clickLoginButton() {
        System.out.println("📍 Click on the login button");
        page.locator("button.radius").first().click();
    }

    public boolean isSuccessMessageDisplayed() {
        System.out.println("📍 Check if success message is displayed");
        return page.locator("div.flash.success").first().isVisible();
    }
}