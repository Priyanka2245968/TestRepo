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
        assertTrue(response.status() >= 200 && response.status() < 300, "Expected successful status");

        JsonNode json = api.asJson(response);
        assertTrue(json.has("bookingid") && json.get("bookingid").isInt(), "Expected bookingid field");
        assertTrue(json.has("booking"), "Expected booking field");
        assertEquals(json.get("booking").get("firstname").asText(), "Swarup");
        assertEquals(json.get("booking").get("lastname").asText(), "Roy");
        assertEquals(json.get("booking").get("totalprice").asInt(), 12000);
        assertTrue(json.get("booking").get("depositpaid").asBoolean());
        assertEquals(json.get("booking").get("bookingdates").get("checkin").asText(), "2026-07-10");
        assertEquals(json.get("booking").get("bookingdates").get("checkout").asText(), "2026-07-12");
        assertEquals(json.get("booking").get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void additionalAcceptanceCriterionBookingIdIsUnique() throws Exception {
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
        assertTrue(response1.status() >= 200 && response1.status() < 300, "Expected successful status");
        JsonNode json1 = api.asJson(response1);
        assertTrue(json1.has("bookingid") && json1.get("bookingid").isInt(), "Expected bookingid field");
        int bookingId1 = json1.get("bookingid").asInt();

        APIResponse response2 = api.post("/booking", body);
        assertTrue(response2.status() >= 200 && response2.status() < 300, "Expected successful status");
        JsonNode json2 = api.asJson(response2);
        assertTrue(json2.has("bookingid") && json2.get("bookingid").isInt(), "Expected bookingid field");
        int bookingId2 = json2.get("bookingid").asInt();

        assertNotEquals(bookingId1, bookingId2, "Booking IDs should be unique");
    }

    @Test
    public void additionalAcceptanceCriterionDataIntegrity() throws Exception {
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

        APIResponse createResponse = api.post("/booking", body);
        assertTrue(createResponse.status() >= 200 && createResponse.status() < 300, "Expected successful status");
        JsonNode createJson = api.asJson(createResponse);
        assertTrue(createJson.has("bookingid") && createJson.get("bookingid").isInt(), "Expected bookingid field");
        int bookingId = createJson.get("bookingid").asInt();

        APIResponse getResponse = api.get("/booking/" + bookingId);
        assertTrue(getResponse.status() >= 200 && getResponse.status() < 300, "Expected successful status");
        JsonNode getJson = api.asJson(getResponse);
        assertEquals(getJson.get("firstname").asText(), "Swarup");
        assertEquals(getJson.get("lastname").asText(), "Roy");
        assertEquals(getJson.get("totalprice").asInt(), 12000);
        assertTrue(getJson.get("depositpaid").asBoolean());
        assertEquals(getJson.get("bookingdates").get("checkin").asText(), "2026-07-10");
        assertEquals(getJson.get("bookingdates").get("checkout").asText(), "2026-07-12");
        assertEquals(getJson.get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void negativeAdditionalNeedsFieldMissing() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-10");
        bookingDates.put("checkout", "2026-07-12");
        body.put("bookingdates", bookingDates);

        APIResponse response = api.post("/booking", body);
        assertTrue(response.status() >= 200 && response.status() < 300, "Expected successful status");
        JsonNode responseBody = api.asJson(response);

        assertTrue(responseBody.has("bookingid"), "Expected bookingid field");
        assertTrue(responseBody.get("bookingid").isInt(), "Expected bookingid to be an integer");
        assertFalse(responseBody.get("bookingid").asText().isEmpty(), "Booking ID should not be empty");
    }
}
