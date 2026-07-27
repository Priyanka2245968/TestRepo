package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.CreateMortgageApplicationPage;

public class CreateMortgageApplicationTest extends BaseTestManager {

    @Test
    public void testCreateMortgageApplication() throws Exception {
        System.out.println("✨ Starting Create Mortgage Application Test");

        CreateMortgageApplicationPage pageObject = new CreateMortgageApplicationPage(this);

        pageObject.navigateToLoginPage();
        pageObject.enterEmailField("borrower@keystone.test");
        pageObject.enterPasswordField("Passw0rd!");
        pageObject.clickSignInButton();
        pageObject.clickApplicationsInSidebar();
        pageObject.clickNewApplicationButton();
        pageObject.enterFirstNameField("John");
        pageObject.enterLastNameField("Doe");
        pageObject.enterEmailField("john.doe@example.com");
        pageObject.enterPhoneField("555-123-4567");
        pageObject.enterMonthlyIncomeField("800000");
        pageObject.enterMonthlyDebtField("200000");
        pageObject.selectProductTypeDropdown("CONV");
        pageObject.selectLoanPurposeDropdown("PURCHASE");
        pageObject.enterRequestedAmountField("30000000");
        pageObject.clickCreateApplicationButton();
        pageObject.navigateBackToApplicationsPage();
        pageObject.clickSignOutButton();

        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}