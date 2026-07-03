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

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-10");
        bookingDates.put("checkout", "2026-07-12");
        body.put("bookingdates", bookingDates);

        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        JsonNode responseJson = api.asJson(response);
        assertTrue(responseJson.get("bookingid").isIntegralNumber());
        JsonNode booking = responseJson.get("booking");
        assertEquals(booking.get("firstname").asText(), "Swarup");
        assertEquals(booking.get("lastname").asText(), "Roy");
        assertEquals(booking.get("totalprice").asInt(), 12000);
        assertTrue(booking.get("depositpaid").asBoolean());
        assertEquals(booking.get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void negativeInvalidInputMissingRequiredField() {
        Map<String, Object> body = new HashMap<>();
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);

        APIResponse response = api.post("/booking", body);
        JsonNode responseJson = api.asJson(response);
        assertTrue(responseJson.get("status").asText().equals("Bad Request"));
    }

    @Test
    public void boundaryCheckinDateAfterCheckoutDate() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-12");
        bookingDates.put("checkout", "2026-07-10");
        body.put("bookingdates", bookingDates);

        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        JsonNode responseJson = api.asJson(response);
        assertTrue(responseJson.get("status").asText().startsWith("4"));
    }
}
