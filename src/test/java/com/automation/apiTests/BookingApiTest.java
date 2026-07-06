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

        JsonNode response = api.post("/booking", body).asJson();
        assertTrue(response.get("bookingid").isInt());
        assertTrue(response.has("booking"));
        JsonNode booking = response.get("booking");
        assertEquals(booking.get("firstname").asText(), "Swarup");
        assertEquals(booking.get("lastname").asText(), "Roy");
        assertEquals(booking.get("totalprice").asInt(), 12000);
        assertTrue(booking.get("depositpaid").asBoolean());
        assertEquals(booking.get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void additionalAcceptanceCriterionBookingIdIsUnique() {
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

        JsonNode response1 = api.post("/booking", body).asJson();
        assertTrue(response1.get("bookingid").isInt());
        int bookingId1 = response1.get("bookingid").asInt();

        JsonNode response2 = api.post("/booking", body).asJson();
        assertTrue(response2.get("bookingid").isInt());
        int bookingId2 = response2.get("bookingid").asInt();

        assertNotEquals(bookingId1, bookingId2);
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

        JsonNode createResponse = api.post("/booking", body).asJson();
        int bookingId = createResponse.get("bookingid").asInt();

        JsonNode getResponse = api.get("/booking/" + bookingId).asJson();
        assertTrue(getResponse.has("firstname"));
        assertTrue(getResponse.has("lastname"));
        assertTrue(getResponse.has("totalprice"));
        assertTrue(getResponse.has("depositpaid"));
        assertTrue(getResponse.has("bookingdates"));
        assertTrue(getResponse.has("additionalneeds"));

        assertEquals(getResponse.get("firstname").asText(), "Swarup");
        assertEquals(getResponse.get("lastname").asText(), "Roy");
        assertEquals(getResponse.get("totalprice").asInt(), 12000);
        assertTrue(getResponse.get("depositpaid").asBoolean());
        assertEquals(getResponse.get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void additionalAcceptanceCriterionOptionalFieldHandling() {
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
        JsonNode responseBody = response.asJson();

        assertTrue(response.status() >= 200 && response.status() < 300, "Expected successful response status");
        assertTrue(responseBody.has("bookingid"), "Expected 'bookingid' field in response");
        assertFalse(responseBody.get("booking").has("additionalneeds"), "'additionalneeds' field should not be present");
    }
}
