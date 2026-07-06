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

import static org.testng.Assert.*;

public class BookingApiTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingApiTest.class);
    private static final String BASE_URL = System.getProperty("apiBaseUrl", "https://restful-booker.herokuapp.com");
    private static final String API_TOKEN = System.getProperty("apiToken", "");

    private ApiUtils api;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        Map<String, String> headers = new HashMap<>();
        if (!API_TOKEN.isEmpty()) {
            headers.put("Authorization", "Bearer " + API_TOKEN);
        }
        api = new ApiUtils(BASE_URL, headers);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        api.dispose();
    }

    @Test
    public void positiveSuccessfulBookingCreation() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        body.put("bookingdates", Map.of("checkin", "2023-05-01", "checkout", "2023-05-05"));
        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        JsonNode jsonResponse = api.asJson(response);
        assertTrue(response.status() >= 200 && response.status() < 300, "Booking creation failed");

        assertTrue(jsonResponse.has("bookingid"), "Response does not contain booking ID");
        int bookingId = jsonResponse.get("bookingid").asInt();
        assertTrue(bookingId > 0, "Invalid booking ID");

        assertEquals(jsonResponse.get("booking").get("firstname").asText(), "Swarup");
        assertEquals(jsonResponse.get("booking").get("lastname").asText(), "Roy");
        assertEquals(jsonResponse.get("booking").get("totalprice").asInt(), 12000);
        assertTrue(jsonResponse.get("booking").get("depositpaid").asBoolean());
        assertEquals(jsonResponse.get("booking").get("bookingdates").get("checkin").asText(), "2023-05-01");
        assertEquals(jsonResponse.get("booking").get("bookingdates").get("checkout").asText(), "2023-05-05");
        assertEquals(jsonResponse.get("booking").get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void negativeInvalidBookingCreation() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "");
        body.put("lastname", "");
        body.put("totalprice", -1);
        body.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-05-01");
        bookingDates.put("checkout", "2023-04-30");
        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        assertFalse(response.status() >= 200 && response.status() < 300, "Booking creation should fail");

        JsonNode jsonResponse = api.asJson(response);
        assertTrue(jsonResponse.has("reason"), "Response does not contain error reason");
        logger.error("Booking creation failed: {}", jsonResponse.get("reason").asText());
    }
}
