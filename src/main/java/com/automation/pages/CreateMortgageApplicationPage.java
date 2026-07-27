package com.automation.pages;

import com.microsoft.playwright.Page;
import com.automation.base.BaseTestManager;

public class CreateMortgageApplicationPage {
    private Page page;

    public CreateMortgageApplicationPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToLoginPage() throws Exception {
        System.out.println("📍 Navigate to the login page");
        page.navigate("http://localhost:1010/login");
    }

    public void enterEmailField(String email) throws Exception {
        System.out.println("📍 Enter username in the email field");
        page.locator("//label[normalize-space(.)=\"Email\"]/following::*[self::input or self::textarea or self::select][1]").first().fill(email);
    }

    public void enterPasswordField(String password) throws Exception {
        System.out.println("📍 Enter password in the password field");
        page.locator("//label[normalize-space(.)=\"Password\"]/following::*[self::input or self::textarea or self::select][1]").first().fill(password);
    }

    public void clickSignInButton() throws Exception {
        System.out.println("📍 Click the Sign In button");
        page.locator("//button[contains(normalize-space(.),\"Sign In\")]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }

    public void clickApplicationsInSidebar() throws Exception {
        System.out.println("📍 Click Applications in the sidebar");
        page.locator("a[href='/applications']").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }

    public void clickNewApplicationButton() throws Exception {
        System.out.println("📍 Click the + New Application button");
        page.locator("a[href='/applications/new']").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }

    public void enterFirstNameField(String firstName) throws Exception {
        System.out.println("📍 Enter first name in the First Name field");
        page.locator("//label[normalize-space(.)=\"First name\"]/following::*[self::input or self::textarea or self::select][1]").first().fill(firstName);
    }

    public void enterLastNameField(String lastName) throws Exception {
        System.out.println("📍 Enter last name in the Last Name field");
        page.locator("//label[normalize-space(.)=\"Last name\"]/following::*[self::input or self::textarea or self::select][1]").first().fill(lastName);
    }

    public void enterEmailField(String email) throws Exception {
        System.out.println("📍 Enter email in the Email field");
        page.locator("//label[normalize-space(.)=\"Email\"]/following::*[self::input or self::textarea or self::select][1]").first().fill(email);
    }

    public void enterPhoneField(String phone) throws Exception {
        System.out.println("📍 Enter phone number in the Phone field");
        page.locator("//label[normalize-space(.)=\"Phone\"]/following::*[self::input or self::textarea or self::select][1]").first().fill(phone);
    }

    public void enterMonthlyIncomeField(String income) throws Exception {
        System.out.println("📍 Enter monthly income in the Monthly Income field");
        page.locator("//label[normalize-space(.)=\"Monthly income (cents)\"]/following::*[self::input or self::textarea or self::select][1]").first().fill(income);
    }

    public void enterMonthlyDebtField(String debt) throws Exception {
        System.out.println("📍 Enter monthly debt in the Monthly Debt field");
        page.locator("//label[normalize-space(.)=\"Monthly debt (cents)\"]/following::*[self::input or self::textarea or self::select][1]").first().fill(debt);
    }

    public void selectProductTypeDropdown(String value) throws Exception {
        System.out.println("📍 Select CONV from the Product Type dropdown");
        page.locator("//label[normalize-space(.)=\"Product\"]/following::*[self::input or self::textarea or self::select][1]").first().selectOption(value);
    }

    public void selectLoanPurposeDropdown(String value) throws Exception {
        System.out.println("📍 Select PURCHASE from the Loan Purpose dropdown");
        page.locator("//label[normalize-space(.)=\"Purpose\"]/following::*[self::input or self::textarea or self::select][1]").first().selectOption(value);
    }

    public void enterRequestedAmountField(String amount) throws Exception {
        System.out.println("📍 Enter requested amount in the Requested Amount field");
        page.locator("//label[normalize-space(.)=\"Requested (cents)\"]/following::*[self::input or self::textarea or self::select][1]").first().fill(amount);
    }

    public void clickCreateApplicationButton() throws Exception {
        System.out.println("📍 Click the Create Application button");
        page.locator("//button[contains(normalize-space(.),\"Create Application\")]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }

    public void navigateBackToApplicationsPage() throws Exception {
        System.out.println("📍 Navigate back to the Applications page");
        page.locator("a[href='/applications']").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }

    public void clickSignOutButton() throws Exception {
        System.out.println("📍 Click the Sign Out button");
        page.locator("//button[contains(normalize-space(.),\"Sign Out\")]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}