package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.BookingPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookingStepDefinitions {
    private BaseTestManager testManager;
    private BookingPage bookingPage;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        bookingPage = new BookingPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        testManager.getPage().navigate(url);
    }

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNum, String description) throws Exception {
        switch (stepNum) {
            case 1:
                bookingPage.createBooking();
                break;
            case 2:
                bookingPage.invalidBookingMissingField();
                break;
            case 3:
                bookingPage.invalidBookingInvalidDates();
                break;
            default:
                throw new Exception("Invalid step number: " + stepNum);
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        bookingPage.takeScreenshot("booking_test_result");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}