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
    private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();
    static {
        String apiToken = System.getProperty("apiToken", "");
        if (!apiToken.isBlank()) {
            DEFAULT_HEADERS.put("Authorization", "Bearer " + apiToken);
        }
        DEFAULT_HEADERS.put("Content-Type", "application/json");
        DEFAULT_HEADERS.put("Accept", "application/json");
    }

    private ApiUtils api;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        logger.info("Setting up API client for base URL: {}", BASE_URL);
        api = new ApiUtils(BASE_URL, DEFAULT_HEADERS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        logger.info("Tearing down API client");
        api.dispose();
    }

    @Test
    public void createBooking() {
        String payload = "{\"firstname\":\"Jim\",\"lastname\":\"Brown\",\"totalprice\":111,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"Breakfast\"}";
        APIResponse response = api.post("/booking", payload);
        assertTrue(response.status() >= 200 && response.status() < 300); // Assert success status class instead of hardcoding
        JsonNode body = api.asJson(response);
        assertNotNull(body.get("bookingid"));
    }

    @Test
    public void getBookingIds() {
        APIResponse response = api.get("/booking");
        assertTrue(response.status() >= 200 && response.status() < 300); // Assert success status class
        JsonNode body = api.asJson(response);
        assertTrue(body.isArray());
        assertTrue(body.size() >= 10); // Asserting the size is at least 10 instead of hardcoding
    }

    @Test
    public void getBooking() {
        int bookingId = 1;
        APIResponse response = api.get("/booking/" + bookingId);
        assertTrue(response.status() >= 200 && response.status() < 300); // Assert success status class
        JsonNode body = api.asJson(response);
        assertNotNull(body.get("firstname"));
        assertNotNull(body.get("lastname"));
        assertNotNull(body.get("totalprice"));
        assertNotNull(body.get("depositpaid"));
        assertNotNull(body.get("bookingdates"));
    }

    @Test
    public void updateBooking() {
        int bookingId = 1;
        String payload = "{\"firstname\":\"James\",\"lastname\":\"Brown\",\"totalprice\":111,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"Breakfast\"}";
        APIResponse response = api.put("/booking/" + bookingId, payload);
        assertTrue(response.status() >= 200 && response.status() < 300); // Assert success status class
        JsonNode updatedBooking = api.asJson(response);
        assertEquals(updatedBooking.get("firstname").asText(), "James");
        assertEquals(updatedBooking.get("lastname").asText(), "Brown");
        assertEquals(updatedBooking.get("totalprice").asInt(), 111);
        assertTrue(updatedBooking.get("depositpaid").asBoolean());
        assertNotNull(updatedBooking.get("bookingdates"));
        assertEquals(updatedBooking.get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void deleteBooking() {
        int bookingId = 1;
        APIResponse response = api.delete("/booking/" + bookingId);
        assertTrue(response.status() >= 200 && response.status() < 300); // Assert success status class
        JsonNode body = api.asJson(response);
        assertTrue(body.get("deleted").asBoolean()); // Assert that the 'deleted' field is true
    }
}
