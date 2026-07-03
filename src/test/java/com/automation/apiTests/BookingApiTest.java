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

    @Test
    public void positiveSuccessfulBookingCreationWithValidPayload() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        body.put("bookingdates", Map.of("checkin", "2023-05-01", "checkout", "2023-05-05"));
        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        assertEquals(response.status(), 200);

        JsonNode jsonResponse = response.json();
        assertTrue(jsonResponse.has("bookingid"));
        assertTrue(jsonResponse.get("booking").has("firstname"));
        assertTrue(jsonResponse.get("booking").has("lastname"));
        assertTrue(jsonResponse.get("booking").has("totalprice"));
        assertTrue(jsonResponse.get("booking").has("depositpaid"));
        assertTrue(jsonResponse.get("booking").has("bookingdates"));
        assertTrue(jsonResponse.get("booking").has("additionalneeds"));
    }
}