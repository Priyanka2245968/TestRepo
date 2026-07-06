package com.automation.apiTests;

import com.automation.utils.ApiUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class BookingApiTest {

    private ApiUtils api;
    private String baseUrl = System.getProperty("apiBaseUrl", "https://restful-booker.herokuapp.com");
    private Map<String, String> headers = new HashMap<>();

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        String apiToken = System.getProperty("apiToken", "");
        if (!apiToken.isEmpty()) {
            headers.put("Authorization", "Bearer " + apiToken);
        }
        api = new ApiUtils(baseUrl, headers);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        api.dispose();
    }

    @Test
    public void positiveSuccessfulBookingCreationWithAllRequiredFields() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-10");
        bookingDates.put("checkout", "2026-07-15");
        body.put("bookingdates", bookingDates);

        APIResponse response = api.post("/booking", body);
        assertEquals(response.statusCode(), 200, "Booking creation failed");
        JsonNode responseBody = response.json();
        assertTrue(responseBody.has("bookingid"), "Response does not contain booking ID");
        int bookingId = responseBody.get("bookingid").asInt();

        // Clean up the created booking
        api.delete("/booking/" + bookingId, headers);
    }
}
