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
    public void positiveSuccessfulBookingCreationWithAllRequiredFields() {
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

        APIResponse response = api.post("/booking", body);
        assertTrue(response.status() >= 200 && response.status() < 300);

        JsonNode json = api.asJson(response);
        assertTrue(json.has("bookingid"));
        assertFalse(json.get("bookingid").asText().isEmpty());
        assertTrue(json.has("booking"));
        assertEquals(json.get("booking").get("firstname").asText(), "Swarup");
        assertEquals(json.get("booking").get("lastname").asText(), "Roy");
        assertEquals(json.get("booking").get("totalprice").asInt(), 12000);
        assertTrue(json.get("booking").get("depositpaid").asBoolean());
        assertEquals(json.get("booking").get("bookingdates").get("checkin").asText(), "2026-07-10");
        assertEquals(json.get("booking").get("bookingdates").get("checkout").asText(), "2026-07-12");
        assertEquals(json.get("booking").get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void positiveBookingIdIsUniqueForEachRequest() {
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

        APIResponse response1 = api.post("/booking", body);
        assertTrue(response1.status() >= 200 && response1.status() < 300);
        JsonNode json1 = api.asJson(response1);
        int bookingId1 = json1.get("bookingid").asInt();

        APIResponse response2 = api.post("/booking", body);
        assertTrue(response2.status() >= 200 && response2.status() < 300);
        JsonNode json2 = api.asJson(response2);
        int bookingId2 = json2.get("bookingid").asInt();

        assertNotEquals(bookingId1, bookingId2);
    }

    @Test
    public void positiveDataIntegrityCheckViaGetBooking() {
        int bookingId = 101;
        APIResponse response = api.get("/booking/" + bookingId);
        assertTrue(response.status() >= 200 && response.status() < 300);

        JsonNode json = api.asJson(response);
        assertEquals(json.get("firstname").asText(), "Sally");
        assertEquals(json.get("lastname").asText(), "Brown");
        assertEquals(json.get("totalprice").asInt(), 111);
        assertFalse(json.get("depositpaid").asBoolean());
        assertEquals(json.get("bookingdates").get("checkin").asText(), "2023-02-01");
        assertEquals(json.get("bookingdates").get("checkout").asText(), "2023-02-05");
        assertEquals(json.get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void positiveOptionalAdditionalneedsFieldHandling() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);

        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        body.put("bookingdates", dates);

        APIResponse response = api.post("/booking", body);
        JsonNode json = api.asJson(response);
        assertTrue(json.has("bookingid"));
        assertTrue(json.get("bookingid").isInt());
        assertTrue(json.get("bookingid").asInt() > 0);
        assertTrue(json.has("additionalneeds") && (json.get("additionalneeds").isNull() || json.get("additionalneeds").asText().isEmpty()));
    }

    @Test
    public void negativeMissingRequiredFirstnameField() {
        Map<String, Object> body = new HashMap<>();
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);

        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        body.put("bookingdates", dates);
        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        int status = response.status();
        assertTrue(status >= 400 && status < 500);
    }
}
