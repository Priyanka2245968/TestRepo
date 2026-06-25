package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;

import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BookingPage {
    private Page page;
    private APIRequestContext apiContext;
    private String baseUrl = "https://restful-booker.herokuapp.com";

    public BookingPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.apiContext = testManager.getContext().request();
    }

    public void createBooking() {
        System.out.println("\ud83d\udccd Creating a new booking");
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

        APIResponse response = apiContext.post(baseUrl + "/booking", body);
        assertEquals(response.statusText(), "OK", "Failed to create booking");

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