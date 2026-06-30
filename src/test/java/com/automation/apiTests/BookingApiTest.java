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
import static org.testng.Assert.assertTrue;

public class BookingApiTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingApiTest.class);
    private static final String BASE_URL = System.getProperty("apiBaseUrl", "https://restful-booker.herokuapp.com");
    private ApiUtils api;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        Map<String, String> headers = new HashMap<>();
        String apiToken = System.getProperty("apiToken", "");
        if (!apiToken.isEmpty()) {
            headers.put("Authorization", "Bearer " + apiToken);
        }
        api = new ApiUtils(BASE_URL, headers);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        api.dispose();
    }

    @Test(description = "BOK-25-TC-01: Positive — Create a valid booking")
    public void createValidBooking() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "John");
        payload.put("lastname", "Doe");
        payload.put("totalprice", 120);
        payload.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-06-01");
        bookingDates.put("checkout", "2023-06-03");
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", payload);
        assertTrue(response.status() >= 200 && response.status() < 300, "Expected successful response");

        JsonNode body = api.asJson(response);
        assertTrue(body.has("bookingid") && body.get("bookingid").isInt(), "Response should contain 'bookingid' field with integer value");
        assertTrue(body.has("booking"), "Response should contain 'booking' object");
        JsonNode booking = body.get("booking");
        assertEquals(booking.get("firstname").asText(), "John", "'firstname' mismatch");
        assertEquals(booking.get("lastname").asText(), "Doe", "'lastname' mismatch");
        assertEquals(booking.get("totalprice").asInt(), 120, "'totalprice' mismatch");
        assertEquals(booking.get("depositpaid").asBoolean(), true, "'depositpaid' mismatch");
        JsonNode dates = booking.get("bookingdates");
        assertEquals(dates.get("checkin").asText(), "2023-06-01", "'checkin' mismatch");
        assertEquals(dates.get("checkout").asText(), "2023-06-03", "'checkout' mismatch");
        assertEquals(booking.get("additionalneeds").asText(), "Breakfast", "'additionalneeds' mismatch");
    }

    @Test(description = "BOK-25-TC-02: Negative — Invalid Input: Missing required field 'firstname'")
    public void missingFirstname() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("lastname", "Doe");
        payload.put("totalprice", 120);
        payload.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-06-01");
        bookingDates.put("checkout", "2023-06-03");
        payload.put("bookingdates", bookingDates);

        APIResponse response = api.post("/booking", payload);
        assertTrue(response.status() >= 400 && response.status() < 500, "Expected client error response");

        JsonNode body = api.asJson(response);
        assertEquals(body.get("error").asText(), "Invalid JSON payload received", "Unexpected error message");
    }

    @Test(description = "BOK-25-TC-03: Boundary — Checkout date before checkin date")
    public void checkoutBeforeCheckin() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Jane");
        payload.put("lastname", "Doe");
        payload.put("totalprice", 120);
        payload.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-06-03");
        bookingDates.put("checkout", "2023-06-01");
        payload.put("bookingdates", bookingDates);

        APIResponse response = api.post("/booking", payload);
        assertTrue(response.status() >= 400 && response.status() < 500, "Expected client error response");

        JsonNode body = api.asJson(response);
        assertEquals(body.get("error").asText(), "Invalid checkin/checkout date range", "Unexpected error message");
    }
}
