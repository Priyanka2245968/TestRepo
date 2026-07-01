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

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
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
        if (api != null) {
            api.dispose();
        }
    }

    @Test(description = "BOK-25-TC-01 Positive — Create a valid booking and receive a unique booking ID")
    public void createValidBooking_receivesUniqueBookingId() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Swarup");
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        payload.put("bookingdates", dates);
        payload.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", payload);

        assertTrue(response.ok(), "Expected a successful (2xx) response");
        JsonNode body = api.asJson(response);
        assertTrue(body.has("bookingid"), "Response should contain a 'bookingid' field");
        assertTrue(body.has("booking"), "Response should contain a 'booking' object");
        JsonNode booking = body.get("booking");
        assertEquals(booking.get("firstname").asText(), "Swarup", "'firstname' mismatch");
        assertEquals(booking.get("lastname").asText(), "Roy", "'lastname' mismatch");
        assertEquals(booking.get("totalprice").asInt(), 12000, "'totalprice' mismatch");
        assertTrue(booking.get("depositpaid").asBoolean(), "'depositpaid' mismatch");
        JsonNode dates = booking.get("bookingdates");
        assertEquals(dates.get("checkin").asText(), "2026-07-10", "'checkin' mismatch");
        assertEquals(dates.get("checkout").asText(), "2026-07-12", "'checkout' mismatch");
        assertEquals(booking.get("additionalneeds").asText(), "Breakfast", "'additionalneeds' mismatch");
    }

    @Test(description = "BOK-25-TC-02 Negative — Invalid Input: Missing required field 'firstname'")
    public void missingFirstname_returns400BadRequest() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        payload.put("bookingdates", dates);
        payload.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", payload);

        assertTrue(response.status() >= 400 && response.status() < 500, "Expected a 4xx client error");
        assertEquals(response.status(), 400, "Expected 400 Bad Request status code");
        JsonNode body = api.asJson(response);
        assertTrue(body.has("reason"), "Response should contain an error 'reason'");
        assertTrue(body.get("reason").asText().toLowerCase().contains("firstname"), "Error reason should mention missing 'firstname'");
    }

    @Test(description = "BOK-25-TC-03 Boundary — Checkin date after or equal to Checkout date")
    public void checkinAfterCheckout_returnsClientError() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Swarup");
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-13");
        dates.put("checkout", "2026-07-12");
        payload.put("bookingdates", dates);
        payload.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", payload);

        assertTrue(response.status() >= 400 && response.status() < 500, "Expected a 4xx client error");
        JsonNode body = api.asJson(response);
        assertTrue(body.has("reason"), "Response should contain an error 'reason'");
        assertTrue(body.get("reason").asText().toLowerCase().contains("date"), "Error reason should mention invalid date range");
    }

    @Test(description = "BOK-25-TC-04 Negative — Duplicate booking attempt")
    public void duplicateBooking_returns409Conflict() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Swarup");
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        payload.put("bookingdates", dates);
        payload.put("additionalneeds", "Breakfast");

        APIResponse response1 = api.post("/booking", payload);
        assertTrue(response1.ok(), "Expected a successful (2xx) response for initial booking");

        APIResponse response2 = api.post("/booking", payload);

        assertEquals(response2.status(), 409, "Expected 409 Conflict status code for duplicate booking");
        JsonNode body = api.asJson(response2);
        assertTrue(body.has("reason"), "Response should contain an error 'reason'");
        assertTrue(body.get("reason").asText().toLowerCase().contains("duplicate"), "Error reason should mention duplicate booking");
    }

    @Test(description = "BOK-25-TC-05 Contract — Response schema validation")
    public void validateResponseSchema() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Swarup");
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-10");
        dates.put("checkout", "2026-07-12");
        payload.put("bookingdates", dates);
        payload.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", payload);

        assertTrue(response.ok(), "Expected a successful (2xx) response");
        assertEquals(response.headers().get("Content-Type"), "application/json", "Expected Content-Type: application/json");
        JsonNode body = api.asJson(response);
        assertTrue(body.has("bookingid"), "Response should contain a 'bookingid' field");
        assertTrue(body.get("bookingid").isIntegralNumber(), "'bookingid' should be an integer");
        assertTrue(body.has("booking"), "Response should contain a 'booking' object");
        JsonNode booking = body.get("booking");
        assertTrue(booking.has("firstname") && booking.get("firstname").isTextual(), "'booking' should contain a string 'firstname' field");
        assertTrue(booking.has("lastname") && booking.get("lastname").isTextual(), "'booking' should contain a string 'lastname' field");
        assertTrue(booking.has("totalprice") && booking.get("totalprice").isIntegralNumber(), "'booking' should contain an integer 'totalprice' field");
        assertTrue(booking.has("depositpaid") && booking.get("depositpaid").isBoolean(), "'booking' should contain a boolean 'depositpaid' field");
        assertTrue(booking.has("bookingdates") && booking.get("bookingdates").isObject(), "'booking' should contain an object 'bookingdates'");
        JsonNode dates = booking.get("bookingdates");
        assertTrue(dates.has("checkin") && dates.get("checkin").isTextual(), "'bookingdates' should contain a string 'checkin' field");
        assertTrue(dates.has("checkout") && dates.get("checkout").isTextual(), "'bookingdates' should contain a string 'checkout' field");
        assertTrue(booking.has("additionalneeds") && booking.get("additionalneeds").isTextual(), "'booking' should contain a string 'additionalneeds' field");
    }
}
