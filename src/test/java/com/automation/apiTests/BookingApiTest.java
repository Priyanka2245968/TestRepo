package com.automation.apiTests;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class BookingApiTest {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private APIRequestContext apiContext;
    private ObjectMapper objectMapper;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        apiContext = BaseTestManager.getApiRequestContext();
        objectMapper = new ObjectMapper();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (apiContext != null) {
            apiContext.dispose();
        }
    }

    @Test(description = "BOK-25-TC-01 - Positive: Create a new booking with valid request payload")
    public void createBookingWithValidPayload() throws Exception {
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

        APIRequest request = apiContext.post("/booking", playwright.playwright.Request.HeadersData.newBuilder()
                .putHeader("Content-Type", "application/json")
                .build(), objectMapper.writeValueAsString(payload));
        APIResponse response = request.get();

        assertEquals(response.status(), 200, "Response status is not 200 OK");
        JsonNode responseBody = objectMapper.readTree(response.text());
        assertTrue(responseBody.has("bookingid"), "Response body does not contain 'bookingid' field");
        assertTrue(responseBody.get("bookingid").isInt(), "'bookingid' is not an integer");
        assertTrue(responseBody.has("booking"), "Response body does not contain 'booking' object");
        JsonNode bookingObject = responseBody.get("booking");
        assertEquals(bookingObject.get("firstname").asText(), "Swarup", "'firstname' does not match request");
        assertEquals(bookingObject.get("lastname").asText(), "Roy", "'lastname' does not match request");
        assertEquals(bookingObject.get("totalprice").asInt(), 12000, "'totalprice' does not match request");
        assertTrue(bookingObject.get("depositpaid").asBoolean(), "'depositpaid' does not match request");
        JsonNode bookingDatesResponse = bookingObject.get("bookingdates");
        assertEquals(bookingDatesResponse.get("checkin").asText(), "2026-07-10", "'checkin' does not match request");
        assertEquals(bookingDatesResponse.get("checkout").asText(), "2026-07-12", "'checkout' does not match request");
        assertEquals(bookingObject.get("additionalneeds").asText(), "Breakfast", "'additionalneeds' does not match request");
    }

    @Test(description = "BOK-25-TC-02 - Negative: Invalid Input - Missing required field")
    public void createBookingWithMissingField() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-10");
        bookingDates.put("checkout", "2026-07-12");
        payload.put("bookingdates", bookingDates);

        APIRequest request = apiContext.post("/booking", playwright.playwright.Request.HeadersData.newBuilder()
                .putHeader("Content-Type", "application/json")
                .build(), objectMapper.writeValueAsString(payload));
        APIResponse response = request.get();

        assertEquals(response.status(), 400, "Response status is not 400 Bad Request");
        assertTrue(response.text().contains("error"), "Response body does not contain an error message");
    }

    @Test(description = "BOK-25-TC-03 - Boundary: Checkin date is after checkout date")
    public void createBookingWithInvalidDates() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Swarup");
        payload.put("lastname", "Roy");
        payload.put("totalprice", 12000);
        payload.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-07-15");
        bookingDates.put("checkout", "2026-07-10");
        payload.put("bookingdates", bookingDates);

        APIRequest request = apiContext.post("/booking", playwright.playwright.Request.HeadersData.newBuilder()
                .putHeader("Content-Type", "application/json")
                .build(), objectMapper.writeValueAsString(payload));
        APIResponse response = request.get();

        assertTrue(response.status() >= 400 && response.status() < 500, "Response status is not a 4xx client error");
        assertTrue(response.text().contains("error"), "Response body does not contain an error message");
    }

    @Test(description = "BOK-25-TC-04 - Contract: Response schema validation")
    public void validateResponseSchema() throws Exception {
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

        APIRequest request = apiContext.post("/booking", playwright.playwright.Request.HeadersData.newBuilder()
                .putHeader("Content-Type", "application/json")
                .build(), objectMapper.writeValueAsString(payload));
        APIResponse response = request.get();

        assertEquals(response.status(), 200, "Response status is not 200 OK");
        assertEquals(response.headers().get("Content-Type"), "application/json", "Response Content-Type header is not application/json");
        JsonNode responseBody = objectMapper.readTree(response.text());
        assertTrue(responseBody.has("bookingid"), "Response body does not contain 'bookingid' field");
        assertTrue(responseBody.get("bookingid").isInt(), "'bookingid' is not an integer");
        assertTrue(responseBody.has("booking"), "Response body does not contain 'booking' object");
        JsonNode bookingObject = responseBody.get("booking");
        assertEquals(bookingObject.get("firstname").asText(), "Swarup", "'firstname' does not match request");
        assertEquals(bookingObject.get("lastname").asText(), "Roy", "'lastname' does not match request");
        assertEquals(bookingObject.get("totalprice").asInt(), 12000, "'totalprice' does not match request");
        assertTrue(bookingObject.get("depositpaid").asBoolean(), "'depositpaid' does not match request");
        JsonNode bookingDatesResponse = bookingObject.get("bookingdates");
        assertEquals(bookingDatesResponse.get("checkin").asText(), "2026-07-10", "'checkin' does not match request");
        assertEquals(bookingDatesResponse.get("checkout").asText(), "2026-07-12", "'checkout' does not match request");
        assertEquals(bookingObject.get("additionalneeds").asText(), "Breakfast", "'additionalneeds' does not match request");
    }
}
