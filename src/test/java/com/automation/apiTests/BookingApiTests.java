package com.automation.apiTests;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BookingApiTests {
    private APIRequestContext apiContext;
    private String baseUrl = "https://restful-booker.herokuapp.com";

    @BeforeClass
    public void setup() {
        apiContext = BaseTestManager.getPlaywrightContext().request();
    }

    @Test(description = "BOK-25-TC-01 - Positive \u2014 Create a new booking")
    public void createBookingPositive() {
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

        APIResponse response = apiContext.post(baseUrl + "/booking", com.microsoft.playwright.APIRequest.newContext(body));
        assertEquals(response.statusText(), "OK", "Failed to create booking");

        JsonNode responseBody = response.json();
        assertNotNull(responseBody.get("bookingid"), "Booking ID is null");
        assertTrue(responseBody.get("booking").get("depositpaid").asBoolean(), "Deposit paid is false");
    }
}