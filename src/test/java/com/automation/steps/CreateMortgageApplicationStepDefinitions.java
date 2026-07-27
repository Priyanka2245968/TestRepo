package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.CreateMortgageApplicationPage;

public class CreateMortgageApplicationStepDefinitions {
    private BaseTestManager testManager;
    private CreateMortgageApplicationPage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new CreateMortgageApplicationPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        System.out.println("🌐 Navigating to: " + url);
        testManager.getPage().navigate(url);
    }

    @When("I enter {string} in the email field")
    public void iEnterInTheEmailField(String email) throws Exception {
        pageObject.enterEmailField(email);
    }

    @When("I enter {string} in the password field")
    public void iEnterInThePasswordField(String password) throws Exception {
        pageObject.enterPasswordField(password);
    }

    @When("I click the Sign In button")
    public void iClickTheSignInButton() throws Exception {
        pageObject.clickSignInButton();
    }

    @When("I click Applications in the sidebar")
    public void iClickApplicationsInTheSidebar() throws Exception {
        pageObject.clickApplicationsInSidebar();
    }

    @When("I click the + New Application button")
    public void iClickTheNewApplicationButton() throws Exception {
        pageObject.clickNewApplicationButton();
    }

    @When("I enter {string} in the First Name field")
    public void iEnterInTheFirstNameField(String firstName) throws Exception {
        pageObject.enterFirstNameField(firstName);
    }

    @When("I enter {string} in the Last Name field")
    public void iEnterInTheLastNameField(String lastName) throws Exception {
        pageObject.enterLastNameField(lastName);
    }

    @When("I enter {string} in the Email field")
    public void iEnterInTheEmailField(String email) throws Exception {
        pageObject.enterEmailField(email);
    }

    @When("I enter {string} in the Phone field")
    public void iEnterInThePhoneField(String phone) throws Exception {
        pageObject.enterPhoneField(phone);
    }

    @When("I enter {string} in the Monthly Income field")
    public void iEnterInTheMonthlyIncomeField(String income) throws Exception {
        pageObject.enterMonthlyIncomeField(income);
    }

    @When("I enter {string} in the Monthly Debt field")
    public void iEnterInTheMonthlyDebtField(String debt) throws Exception {
        pageObject.enterMonthlyDebtField(debt);
    }

    @When("I select {string} from the Product Type dropdown")
    public void iSelectFromTheProductTypeDropdown(String value) throws Exception {
        pageObject.selectProductTypeDropdown(value);
    }

    @When("I select {string} from the Loan Purpose dropdown")
    public void iSelectFromTheLoanPurposeDropdown(String value) throws Exception {
        pageObject.selectLoanPurposeDropdown(value);
    }

    @When("I enter {string} in the Requested Amount field")
    public void iEnterInTheRequestedAmountField(String amount) throws Exception {
        pageObject.enterRequestedAmountField(amount);
    }

    @When("I click the Create Application button")
    public void iClickTheCreateApplicationButton() throws Exception {
        pageObject.clickCreateApplicationButton();
    }

    @When("I navigate back to the Applications page")
    public void iNavigateBackToTheApplicationsPage() throws Exception {
        pageObject.navigateBackToApplicationsPage();
    }

    @When("I click the Sign Out button")
    public void iClickTheSignOutButton() throws Exception {
        pageObject.clickSignOutButton();
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("bdd-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}