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
        Locator usernameField = page.locator("Username field");
        usernameField.fill(username);
    }

    public void enterPassword(String password) {
        System.out.println("📍 Enter password in the password field");
        Locator passwordField = page.locator("Password field");
        passwordField.fill(password);
    }

    public void clickLoginButton() {
        System.out.println("📍 Click on the login button");
        Locator loginButton = page.locator("Login button");
        loginButton.click();
    }
}