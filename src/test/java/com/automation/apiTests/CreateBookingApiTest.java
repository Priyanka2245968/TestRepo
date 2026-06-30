package com.automation.apiTests;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CreateBookingApiTest {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private APIRequestContext apiContext;
    private ObjectMapper objectMapper;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        apiContext = BaseTestManager.getPlaywrightContext().request();
        objectMapper = new ObjectMapper();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (apiContext != null) {
            apiContext.dispose();
        }
    }

    @Test(description = "BOK-25-TC-01 - Positive — Create a new booking with valid payload")
    public void createBookingWithValidPayload() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Swarup");
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-10");
        bookingDates.put("checkout", "2026-07-12");
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        APIRequest request = apiContext.post("/booking", requestContext -> requestContext.setHeader("Content-Type", "application/json").setBody(objectMapper.writeValueAsString(payload)));
        APIResponse response = request.get();
        assertEquals(response.status(), 200, "Response status is not 200 OK");
        JsonNode responseBody = objectMapper.readTree(response.body());
        assertNotNull(responseBody.get("bookingid"), "Response does not contain bookingid");
        assertTrue(responseBody.get("bookingid").isInt(), "bookingid is not an integer");
        assertNotNull(responseBody.get("booking"), "Response does not contain booking object");
    }

    @Test(description = "BOK-25-TC-02 - Negative — Invalid Input: Missing required field")
    public void createBookingWithMissingField() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-10");
        bookingDates.put("checkout", "2026-07-12");
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        APIRequest request = apiContext.post("/booking", requestContext -> requestContext.setHeader("Content-Type", "application/json").setBody(objectMapper.writeValueAsString(payload)));
        APIResponse response = request.get();
        assertTrue(response.status() >= 400 && response.status() < 500, "Response status is not a 4xx error");
        JsonNode responseBody = objectMapper.readTree(response.body());
        assertTrue(responseBody.has("reason"), "Response does not contain an error reason");
        assertTrue(responseBody.get("reason").asText().toLowerCase().contains("firstname"), "Error reason does not mention missing firstname field");
    }

    @Test(description = "BOK-25-TC-03 - Boundary — Checkin date equal to or after checkout date")
    public void createBookingWithInvalidDateRange() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Swarup");
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-12");
        bookingDates.put("checkout", "2026-07-12");
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        APIRequest request = apiContext.post("/booking", requestContext -> requestContext.setHeader("Content-Type", "application/json").setBody(objectMapper.writeValueAsString(payload)));
        APIResponse response = request.get();
        assertTrue(response.status() >= 400 && response.status() < 500, "Response status is not a 4xx error");
        JsonNode responseBody = objectMapper.readTree(response.body());
        assertTrue(responseBody.has("reason"), "Response does not contain an error reason");
        assertTrue(responseBody.get("reason").asText().toLowerCase().contains("invalid date range"), "Error reason does not mention invalid date range");
    }
}