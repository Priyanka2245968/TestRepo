package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BookingPage {
    private BaseTestManager testManager;
    private APIRequestContext apiContext;
    private String baseUrl = "https://restful-booker.herokuapp.com";

    public BookingPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.apiContext = testManager.getPlaywrightContext().request();
    }

    public void createBooking() {
        System.out.println("📍 Creating a new booking");
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        body.put("bookingdates", dates);
        body.put("additionalneeds", "Breakfast");

        APIResponse response = apiContext.post(baseUrl + "/booking", APIRequest.BodyType.JSON, body);
        int statusCode = response.statusCode();
        assertEquals(statusCode, 200, "Failed to create booking");

        JsonNode responseBody = response.json();
        assertNotNull(responseBody.get("bookingid"), "Booking ID is null");
        assertTrue(responseBody.get("booking").get("depositpaid").asBoolean(), "Deposit paid is false");
    }

    public void invalidBookingMissingField() {
        // Implementation missing
    }

    public void invalidBookingInvalidDates() {
        // Implementation missing
    }
}