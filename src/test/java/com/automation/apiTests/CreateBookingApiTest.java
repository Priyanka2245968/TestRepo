package com.automation.apiTests;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CreateBookingApiTest {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private BaseTestManager baseTestManager;
    private ObjectMapper objectMapper;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        baseTestManager = new BaseTestManager();
        baseTestManager.initializePlaywrightContext();
        objectMapper = new ObjectMapper();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (baseTestManager != null) {
            baseTestManager.closePlaywrightContext();
        }
    }

    @Test(description = "BOK-25-TC-01 - Positive — Create a new booking with valid payload")
    public void createBookingWithValidPayload() throws IOException {
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

        JsonNode response = baseTestManager.sendPostRequest(BASE_URL + "/booking", payload);

        assertNotNull(response);
        assertTrue(response.has("bookingid"));
        assertEquals(response.get("booking").get("firstname").asText(), "Swarup");
        assertEquals(response.get("booking").get("lastname").asText(), "Roy");
        assertEquals(response.get("booking").get("totalprice").asInt(), 12000);
        assertTrue(response.get("booking").get("depositpaid").asBoolean());
        assertEquals(response.get("booking").get("bookingdates").get("checkin").asText(), "2026-07-10");
        assertEquals(response.get("booking").get("bookingdates").get("checkout").asText(), "2026-07-12");
        assertEquals(response.get("booking").get("additionalneeds").asText(), "Breakfast");
    }

    // Add more test cases as needed
}
