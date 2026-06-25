package com.automation.apiTests;

import com.automation.utils.ApiUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BookingApiTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingApiTest.class);
    private static final String BASE_URL = System.getProperty("apiBaseUrl", "https://restful-booker.herokuapp.com");

    private ApiUtils api;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        logger.info("Setting up API client for base URL: {}", BASE_URL);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        api = new ApiUtils(BASE_URL, headers);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (api != null) {
            api.dispose();
        }
    }

    @Test(description = "Positive — Create a valid booking")
    public void createValidBooking_returnsBookingId() {
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

        APIResponse response = api.post("/booking", payload);

        assertEquals(response.status(), 201, "Expected 201 Created status");

        JsonNode body = api.asJson(response);
        assertNotNull(body.get("bookingid"), "Response should contain a 'bookingid' field");
        assertTrue(body.get("bookingid").isInt(), "'bookingid' should be an integer");
    }
}
