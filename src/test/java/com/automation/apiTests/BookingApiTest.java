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

        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        body.put("bookingdates", dates);

        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        assertTrue(response.status() >= 200 && response.status() < 300);

        JsonNode json = api.asJson(response);
        assertTrue(json.has("bookingid"));
        assertNotEquals(json.get("bookingid").asInt(), 0);
        assertTrue(json.has("booking"));
        JsonNode booking = json.get("booking");
        assertEquals(booking.get("firstname").asText(), "Swarup");
        assertEquals(booking.get("lastname").asText(), "Roy");
        assertEquals(booking.get("totalprice").asInt(), 12000);
        assertTrue(booking.get("depositpaid").asBoolean());
        JsonNode dates2 = booking.get("bookingdates");
        assertEquals(dates2.get("checkin").asText(), "2026-07-10");
        assertEquals(dates2.get("checkout").asText(), "2026-07-12");
        assertEquals(booking.get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void additionalAcceptanceCriterionBookingIDIsUnique() {
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
        assertTrue(json1.has("bookingid"));
        int bookingId1 = json1.get("bookingid").asInt();
        assertNotEquals(bookingId1, 0);

        APIResponse response2 = api.post("/booking", body);
        assertTrue(response2.status() >= 200 && response2.status() < 300);
        JsonNode json2 = api.asJson(response2);
        assertTrue(json2.has("bookingid"));
        int bookingId2 = json2.get("bookingid").asInt();
        assertNotEquals(bookingId2, 0);
        assertNotEquals(bookingId2, bookingId1);
    }

    @Test
    public void additionalAcceptanceCriterionDataIntegrity() {
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
        assertTrue(createResponse.status() >= 200 && createResponse.status() < 300);
        JsonNode createJson = api.asJson(createResponse);
        assertTrue(createJson.has("bookingid"));
        int bookingId = createJson.get("bookingid").asInt();
        assertNotEquals(bookingId, 0);

        APIResponse getResponse = api.get("/booking/" + bookingId);
        assertTrue(getResponse.status() >= 200 && getResponse.status() < 300);
        JsonNode getJson = api.asJson(getResponse);
        JsonNode booking = getJson.get("booking");
        assertEquals(booking.get("firstname").asText(), "Swarup");
        assertEquals(booking.get("lastname").asText(), "Roy");
        assertEquals(booking.get("totalprice").asInt(), 12000);
        assertTrue(booking.get("depositpaid").asBoolean());
        JsonNode dates2 = booking.get("bookingdates");
        assertEquals(dates2.get("checkin").asText(), "2026-07-10");
        assertEquals(dates2.get("checkout").asText(), "2026-07-12");
        assertEquals(booking.get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void negativeRequestWithoutAdditionalNeedsField() {
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
        assertTrue(response.status() >= 200 && response.status() < 300, "Expected successful response status");

        JsonNode responseBody = api.asJson(response);
        assertNotNull(responseBody.get("bookingid"), "Expected 'bookingid' field in response");
        assertTrue(responseBody.get("bookingid").isInt(), "Expected 'bookingid' to be an integer");
        assertNotNull(responseBody.get("booking"), "Expected 'booking' field in response");
        JsonNode additionalNeeds = responseBody.get("booking").get("additionalneeds");
        assertTrue(additionalNeeds == null || additionalNeeds.isNull(), "Expected 'additionalneeds' to be null or not present");
    }
}
