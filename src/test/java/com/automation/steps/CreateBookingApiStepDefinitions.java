package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIResponse;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CreateBookingApiStepDefinitions {
    private BaseTestManager testManager;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        testManager.getPage().navigate(url);
    }

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNumber, String description) throws Exception {
        Map<String, Object> payload = new HashMap<>();
        switch (stepNumber) {
            case 1:
                if (description.contains("'firstname':'Swarup'")) {
                    payload.put("firstname", "Swarup");
                }
                if (description.contains("'lastname':'Roy'")) {
                    payload.put("lastname", "Roy");
                }
                payload.put("totalprice", 12000);
                payload.put("depositpaid", true);
                Map<String, String> bookingDates = new HashMap<>();
                if (description.contains("'checkin':'2026-07-10'")) {
                    bookingDates.put("checkin", "2026-07-10");
                } else if (description.contains("'checkin':'2026-07-12'")) {
                    bookingDates.put("checkin", "2026-07-12");
                }
                if (description.contains("'checkout':'2026-07-12'")) {
                    bookingDates.put("checkout", "2026-07-12");
                }
                payload.put("bookingdates", bookingDates);
                payload.put("additionalneeds", "Breakfast");

                APIRequest request = testManager.getPlaywrightContext().request().post("/booking", requestContext -> requestContext.setHeader("Content-Type", "application/json").setBody(objectMapper.writeValueAsString(payload)));
                APIResponse response = request.get();
                if (description.contains("valid payload")) {
                    assertEquals(response.status(), 200, "Response status is not 200 OK");
                    JsonNode responseBody = objectMapper.readTree(response.body());
                    assertNotNull(responseBody.get("bookingid"), "Response does not contain bookingid");
                    assertTrue(responseBody.get("bookingid").isInt(), "bookingid is not an integer");
                    assertNotNull(responseBody.get("booking"), "Response does not contain booking object");
                } else if (description.contains("Missing required field")) {
                    assertTrue(response.status() >= 400 && response.status() < 500, "Response status is not a 4xx error");
                    JsonNode responseBody = objectMapper.readTree(response.body());
                    assertTrue(responseBody.has("reason"), "Response does not contain an error reason");
                    assertTrue(responseBody.get("reason").asText().toLowerCase().contains("firstname"), "Error reason does not mention missing firstname field");
                } else if (description.contains("invalid date range")) {
                    assertTrue(response.status() >= 400 && response.status() < 500, "Response status is not a 4xx error");
                    JsonNode responseBody = objectMapper.readTree(response.body());
                    assertTrue(responseBody.has("reason"), "Response does not contain an error reason");
                    assertTrue(responseBody.get("reason").asText().toLowerCase().contains("invalid date range"), "Error reason does not mention invalid date range");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid step number: " + stepNumber);
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        System.out.println("✅ API Test completed successfully!");
    }
}