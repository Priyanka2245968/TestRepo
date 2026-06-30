package com.automation.apiTests;

import com.automation.utils.ApiUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BookingApiTest {

    private ApiUtils api;
    private String baseUrl;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        baseUrl = System.getProperty("apiBaseUrl", "https://api.example.com");
        String apiToken = System.getProperty("apiToken", "");
        Map<String, String> headers = new HashMap<>();
        if (!apiToken.isEmpty()) {
            headers.put("Authorization", "Bearer " + apiToken);
        }
        api = new ApiUtils(baseUrl, headers);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (api != null) {
            api.dispose();
        }
    }

    @Test(description = "API-TC-01: Create a valid booking")
    public void createValidBooking() {
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

        APIResponse response = api.post("/booking", api.toJson(payload));

        assertEquals(response.status(), 201, "Unexpected HTTP status code");

        JsonNode body = api.asJson(response);
        assertTrue(body.has("bookingid"), "Response should contain 'bookingid' field");
        assertFalse(body.get("bookingid").asText().isEmpty(), "'bookingid' should not be empty");
    }
}
