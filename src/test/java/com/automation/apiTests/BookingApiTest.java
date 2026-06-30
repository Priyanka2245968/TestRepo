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

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingApiTest.class);
    private static final String BASE_URL = System.getProperty("apiBaseUrl", "https://restful-booker.herokuapp.com");
    private static final String API_TOKEN = System.getProperty("apiToken", "");
    private ApiUtils apiUtils;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        Map<String, String> headers = new HashMap<>();
        if (!API_TOKEN.isEmpty()) {
            headers.put("Authorization", "Bearer " + API_TOKEN);
        }
        apiUtils = new ApiUtils(BASE_URL, headers);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        apiUtils.dispose();
    }

    @Test(description = "BOK-25-TC-01: Positive — Create a valid booking")
    public void createValidBooking() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "John");
        payload.put("lastname", "Doe");
        payload.put("totalprice", 100);
        payload.put("depositpaid", true);
        payload.put("bookingdates", Map.of("checkin", "2023-06-01", "checkout", "2023-06-05"));
        payload.put("additionalneeds", "Breakfast");

        APIResponse response = apiUtils.post("/booking", payload);
        int statusCode = response.statusCode();
        JsonNode jsonResponse = response.json();

        assertEquals(statusCode, 200, "Failed to create a booking");
        assertTrue(jsonResponse.has("bookingid"), "Response does not contain a booking ID");
        LOGGER.info("Booking created successfully with ID: {}", jsonResponse.get("bookingid").asText());
    }
}
