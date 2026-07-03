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
    public void positiveCreateANewBookingSuccessfully() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "John");
        body.put("lastname", "Doe");
        body.put("totalprice", 120);
        body.put("depositpaid", true);

        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2023-06-01");
        dates.put("checkout", "2023-06-03");
        body.put("bookingdates", dates);

        body.put("additionalneeds", "Breakfast");

        APIResponse response = api.post("/booking", body);
        assertTrue(response.status() >= 200 && response.status() < 300);

        JsonNode json = api.asJson(response);
        assertTrue(json.has("bookingid"));
        assertNotNull(json.get("bookingid").asText());
        assertTrue(json.has("booking"));
    }

    @Test
    public void positiveBookingIDIsUniqueAcrossRequests() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Jane");
        body.put("lastname", "Doe");
        body.put("totalprice", 150);
        body.put("depositpaid", false);

        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2023-07-01");
        dates.put("checkout", "2023-07-05");
        body.put("bookingdates", dates);

        APIResponse response1 = api.post("/booking", body);
        assertTrue(response1.status() >= 200 && response1.status() < 300);
        JsonNode json1 = api.asJson(response1);
        assertTrue(json1.has("bookingid"));
        String bookingId1 = json1.get("bookingid").asText();

        APIResponse response2 = api.post("/booking", body);
        assertTrue(response2.status() >= 200 && response2.status() < 300);
        JsonNode json2 = api.asJson(response2);
        assertTrue(json2.has("bookingid"));
        String bookingId2 = json2.get("bookingid").asText();

        assertNotEquals(bookingId1, bookingId2);
    }

    @Test
    public void negativeMissingRequiredField() {
        Map<String, Object> body = new HashMap<>();
        body.put("lastname", "Smith");
        body.put("totalprice", 200);
        body.put("depositpaid", true);

        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2023-08-01");
        dates.put("checkout", "2023-08-05");
        body.put("bookingdates", dates);

        APIResponse response = api.post("/booking", body);
        assertTrue(response.status() >= 400 && response.status() < 500);

        JsonNode json = api.asJson(response);
        assertTrue(json.has("reason"));
    }

    @Test
    public void boundaryInvalidDateRange() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Bob");
        body.put("lastname", "Smith");
        body.put("totalprice", 180);
        body.put("depositpaid", true);

        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2023-09-15");
        dates.put("checkout", "2023-09-10");
        body.put("bookingdates", dates);

        APIResponse response = api.post("/booking", body);
        assertTrue(response.status() >= 400 && response.status() < 500, "Expected client error status code");

        JsonNode responseBody = api.asJson(response);
        assertTrue(responseBody.has("reason"), "Expected error message in response body");
    }
}
