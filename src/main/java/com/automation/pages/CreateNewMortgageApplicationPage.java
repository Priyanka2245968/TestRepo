package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateNewMortgageApplicationPage {
    private WebDriver driver;

    public CreateNewMortgageApplicationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("http://localhost:1010/login"); }

    public void fill_email_field(String value) {
        driver.findElement(By.cssSelector("//label[normalize-space(.)="Email"]/following::*[self::input or self::textarea or self::select][1]")).sendKeys(value);
    }

    public void fill_password_field(String value) {
        driver.findElement(By.cssSelector("//label[normalize-space(.)="Password"]/following::*[self::input or self::textarea or self::select][1]")).sendKeys(value);
    }

    public void click_Sign_In_button() {
        driver.findElement(By.cssSelector("//button[contains(normalize-space(.),'Sign In')]")).click();
    }

    public void click_Applications() {
        driver.findElement(By.cssSelector("a[href='/applications']")).click();
    }

    public void click___New_Application_button() {
        driver.findElement(By.cssSelector("a[href='/applications/new']")).click();
    }

    public void fill_First_Name_field(String value) {
        driver.findElement(By.cssSelector("//label[normalize-space(.)="First name"]/following::*[self::input or self::textarea or self::select][1]")).sendKeys(value);
    }

    public void fill_Last_Name_field(String value) {
        driver.findElement(By.cssSelector("//label[normalize-space(.)="Last name"]/following::*[self::input or self::textarea or self::select][1]")).sendKeys(value);
    }

    public void fill_Email_field(String value) {
        driver.findElement(By.cssSelector("//label[normalize-space(.)="Email"]/following::*[self::input or self::textarea or self::select][1]")).sendKeys(value);
    }

    public void fill_Phone_field(String value) {
        driver.findElement(By.cssSelector("//label[normalize-space(.)="Phone"]/following::*[self::input or self::textarea or self::select][1]")).sendKeys(value);
    }

    public void fill_Monthly_Income_field(String value) {
        driver.findElement(By.cssSelector("//label[normalize-space(.)="Monthly income (cents)"]/following::*[self::input or self::textarea or self::select][1]")).sendKeys(value);
    }

    public void fill_Monthly_Debt_field(String value) {
        driver.findElement(By.cssSelector("//label[normalize-space(.)="Monthly debt (cents)"]/following::*[self::input or self::textarea or self::select][1]")).sendKeys(value);
    }

    // select Product Type dropdown

    // select Loan Purpose dropdown

    public void fill_Requested_Amount_field(String value) {
        driver.findElement(By.cssSelector("//label[normalize-space(.)="Requested (cents)"]/following::*[self::input or self::textarea or self::select][1]")).sendKeys(value);
    }

    public void click_Create_Application_button() {
        driver.findElement(By.cssSelector("//button[contains(normalize-space(.),'Create Application')]")).click();
    }

    public void click_Applications() {
        driver.findElement(By.cssSelector("a[href='/applications']")).click();
    }

    public void click_Sign_Out_button() {
        driver.findElement(By.cssSelector("//button[contains(normalize-space(.),'Sign Out')]")).click();
    }

    public boolean verify_KeyStone_Mortgage() {
        // Login page should be displayed with the KeyStone Mortgage heading
        return driver.findElement(By.cssSelector("KeyStone Mortgage")).isDisplayed();
    }

    public boolean verify_None() {
        // User should be redirected to the borrower dashboard
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify_Applications_page() {
        // Applications page should be displayed successfully
        return driver.findElement(By.cssSelector("Applications page")).isDisplayed();
    }

    public boolean verify_New_Application_form() {
        // New Application form should be displayed
        return driver.findElement(By.cssSelector("New Application form")).isDisplayed();
    }

    public boolean verify_First_Name_field() {
        // First Name field should have value John
        return driver.findElement(By.cssSelector("First Name field")).isDisplayed();
    }

    public boolean verify_Last_Name_field() {
        // Last Name field should have value Doe
        return driver.findElement(By.cssSelector("Last Name field")).isDisplayed();
    }

    public boolean verify_Email_field() {
        // Email field should have value john.doe@example.com
        return driver.findElement(By.cssSelector("Email field")).isDisplayed();
    }

    public boolean verify_Phone_field() {
        // Phone field should have value 555-123-4567
        return driver.findElement(By.cssSelector("Phone field")).isDisplayed();
    }

    public boolean verify_Monthly_Income_field() {
        // Monthly Income field should have value 800000
        return driver.findElement(By.cssSelector("Monthly Income field")).isDisplayed();
    }

    public boolean verify_Monthly_Debt_field() {
        // Monthly Debt field should have value 200000
        return driver.findElement(By.cssSelector("Monthly Debt field")).isDisplayed();
    }

    public boolean verify_Product_Type_dropdown() {
        // Product Type should display CONV
        return driver.findElement(By.cssSelector("Product Type dropdown")).isDisplayed();
    }

    public boolean verify_Loan_Purpose_dropdown() {
        // Loan Purpose should display PURCHASE
        return driver.findElement(By.cssSelector("Loan Purpose dropdown")).isDisplayed();
    }

    public boolean verify_Requested_Amount_field() {
        // Requested Amount should display 30000000 cents ($300,000)
        return driver.findElement(By.cssSelector("Requested Amount field")).isDisplayed();
    }

    public boolean verify_None() {
        // Application Details page should be displayed
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }

    public boolean verify_Application_status() {
        // Application status should be displayed as DRAFT or the default initial status
        return driver.findElement(By.cssSelector("Application status")).isDisplayed();
    }

    public boolean verify_Newly_created_application() {
        // Newly created application should appear in the Applications list
        return driver.findElement(By.cssSelector("Newly created application")).isDisplayed();
    }

    public boolean verify_None() {
        // User should be logged out successfully and redirected to the login page
        return driver.findElement(By.cssSelector("None")).isDisplayed();
    }
}