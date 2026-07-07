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
    public void positiveSuccessfulBookingCreation() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "James");
        body.put("lastname", "Brown");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        body.put("bookingdates", Map.of("checkin", "2023-05-01", "checkout", "2023-05-05"));
        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        assertTrue(response.status() >= 200 && response.status() < 300);
    }

    @Test
    public void positiveSuccessfulBookingRetrieval() throws Exception {
        APIResponse response = api.get("/booking/1");
        JsonNode booking = api.asJson(response);
        assertNotNull(booking);
        assertEquals(booking.get("firstname").asText(), "James");
        assertEquals(booking.get("lastname").asText(), "Brown");
    }

    @Test
    public void negativeBookingDeletion() throws Exception {
        APIResponse response = api.delete("/booking/1");
        assertTrue(response.status() >= 400 && response.status() < 500);
    }
}
