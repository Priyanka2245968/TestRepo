package com.automation.apiTests;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BookingApiTests {
    private APIRequestContext apiContext;
    private String baseUrl = "https://restful-booker.herokuapp.com";

    @BeforeClass
    public void setup() {
        apiContext = BaseTestManager.getPlaywrightContext().request();
    }

    @Test(description = "BOK-25-TC-01 - Positive — Create a new booking")
    public void createBookingPositive() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        body.put("bookingdates", dates);
        body.put("additionalneeds", "Breakfast");

        APIResponse response = apiContext.post(baseUrl + "/booking", APIRequest.newContext().setHeader("Content-Type", "application/json").setBodyJsonObject(body).build());
        assertEquals(response.status(), 200, "Response status is not 200 OK");
        JsonNode json = response.json();
        assertNotNull(json.get("bookingid"), "'bookingid' field is missing or empty");
        assertTrue(json.get("bookingid").isInt(), "'bookingid' field is not an integer");
        JsonNode booking = json.get("booking");
        assertNotNull(booking, "'booking' field is missing");
        assertEquals(booking.get("firstname").asText(), "Swarup", "'firstname' field mismatch");
        assertEquals(booking.get("lastname").asText(), "Roy", "'lastname' field mismatch");
        assertEquals(booking.get("totalprice").asInt(), 12000, "'totalprice' field mismatch");
        assertTrue(booking.get("depositpaid").asBoolean(), "'depositpaid' field mismatch");
        assertEquals(booking.get("bookingdates").get("checkin").asText(), "2026-07-10", "'checkin' field mismatch");
        assertEquals(booking.get("bookingdates").get("checkout").asText(), "2026-07-12", "'checkout' field mismatch");
        assertEquals(booking.get("additionalneeds").asText(), "Breakfast", "'additionalneeds' field mismatch");
    }

    @Test(description = "BOK-25-TC-02 - Negative — Invalid Input: Missing required field")
    public void createBookingMissingField() {
        Map<String, Object> body = new HashMap<>();
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        body.put("bookingdates", dates);
        body.put("additionalneeds", "Breakfast");

        APIResponse response = apiContext.post(baseUrl + "/booking", APIRequest.newContext().setHeader("Content-Type", "application/json").setBodyJsonObject(body).build());
        assertEquals(response.status(), 400, "Response status is not 400 Bad Request");
    }

    @Test(description = "BOK-25-TC-03 - Boundary — Checkout date before check-in date")
    public void createBookingInvalidDates() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-12");
        dates.put("checkout", "2026-07-10");
        body.put("bookingdates", dates);
        body.put("additionalneeds", "Breakfast");

        APIResponse response = apiContext.post(baseUrl + "/booking", APIRequest.newContext().setHeader("Content-Type", "application/json").setBodyJsonObject(body).build());
        assertTrue(response.status() >= 400 && response.status() < 500, "Response status is not a 4xx error code");
    }
}