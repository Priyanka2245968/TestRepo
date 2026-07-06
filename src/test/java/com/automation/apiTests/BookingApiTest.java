package com.automation.apiTests;

import com.automation.utils.ApiUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class BookingApiTest {

    private ApiUtils api;
    private String baseUrl = System.getProperty("apiBaseUrl", "https://restful-booker.herokuapp.com");
    private Map<String, String> headers = new HashMap<>();

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        String apiToken = System.getProperty("apiToken", "");
        if (!apiToken.isEmpty()) {
            headers.put("Authorization", "Bearer " + apiToken);
        }
        api = new ApiUtils(baseUrl, headers);
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
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-10");
        bookingDates.put("checkout", "2026-07-12");
        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        assertEquals(response.status(), 200);
        JsonNode json = api.asJson(response);
        assertTrue(json.has("bookingid"));
        assertTrue(json.get("bookingid").isInt());
        assertNotEquals(json.get("bookingid").asInt(), 0);
        assertTrue(json.has("booking"));
    }

    @Test
    public void acceptanceCriterionBookingIdIsUnique() {
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

        APIResponse response1 = api.post("/booking", body);
        assertEquals(response1.status(), 200);
        JsonNode json1 = api.asJson(response1);
        assertTrue(json1.has("bookingid"));
        assertTrue(json1.get("bookingid").isInt());
        int bookingId1 = json1.get("bookingid").asInt();
        assertNotEquals(bookingId1, 0);

        APIResponse response2 = api.post("/booking", body);
        assertEquals(response2.status(), 200);
        JsonNode json2 = api.asJson(response2);
        assertTrue(json2.has("bookingid"));
        assertTrue(json2.get("bookingid").isInt());
        int bookingId2 = json2.get("bookingid").asInt();
        assertNotEquals(bookingId2, 0);
        assertNotEquals(bookingId1, bookingId2);
    }

    @Test
    public void negativeRequiredFieldsValidation() {
        Map<String, Object> body = new HashMap<>();
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-10");
        bookingDates.put("checkout", "2026-07-12");
        body.put("bookingdates", bookingDates);

        APIResponse response = api.post("/booking", body);
        assertTrue(response.status() >= 400 && response.status() < 500);
    }

    @Test
    public void negativeDataValidation() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);

        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2026-07-12");
        dates.put("checkout", "2026-07-10");
        body.put("bookingdates", dates);

        APIResponse response = api.post("/booking", body);
        assertTrue(response.status() >= 400 && response.status() < 500, "Expected client error status code");
    }

    @Test
    public void positiveDataIntegrity() {
        // Precondition: A successful booking has been created with a known bookingid
        int bookingId = 123; // Replace with a known valid bookingid

        APIResponse response = api.get("/booking/" + bookingId);
        assertEquals(response.status(), 200, "Expected 200 OK status");

        JsonNode body = api.asJson(response);
        assertNotNull(body, "Response body should not be null");

        // Assert the booking details in the body match the original request payload
        // TODO: Add assertions to validate the response body fields
    }

    @Test
    public void positiveOptionalFieldHandling() {
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
        assertTrue(response.status() >= 200 && response.status() < 300, "Expected success status code");

        JsonNode responseBody = api.asJson(response);
        assertNotNull(responseBody.get("bookingid"), "Response should contain a valid 'bookingid'");
    }
}
