package com.automation.apiTests;

import com.automation.utils.ApiUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIRequest;
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
        logger.info("Tearing down API client");
        api.close();
    }

    @Test
    public void createBooking() {
        String payload = "{\"firstname\":\"Jim\",\"lastname\":\"Brown\",\"totalprice\":111,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"Breakfast\"}";
        APIResponse response = api.post("/booking", payload);
        assertEquals(response.statusText(), "Created");
        assertNotNull(response.json().get("bookingid"));
    }

    @Test
    public void getBookingIds() {
        APIResponse response = api.get("/booking");
        assertEquals(response.statusText(), "OK");
        assertThat(response.json().asList()).hasSize(10);
    }

    @Test
    public void getBooking() {
        int bookingId = 1;
        APIResponse response = api.get("/booking/" + bookingId);
        assertEquals(response.statusText(), "OK");
        assertNotNull(response.json().get("firstname"));
        assertNotNull(response.json().get("lastname"));
        assertNotNull(response.json().get("totalprice"));
        assertNotNull(response.json().get("depositpaid"));
        assertNotNull(response.json().get("bookingdates"));
    }

    @Test
    public void updateBooking() {
        int bookingId = 1;
        String payload = "{\"firstname\":\"James\",\"lastname\":\"Brown\",\"totalprice\":111,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"Breakfast\"}";
        APIResponse response = api.put("/booking/" + bookingId, payload);
        assertEquals(response.statusText(), "OK");
        JsonNode updatedBooking = response.json();
        assertEquals(updatedBooking.get("firstname").asText(), "James");
        assertEquals(updatedBooking.get("lastname").asText(), "Brown");
        assertEquals(updatedBooking.get("totalprice").asInt(), 111);
        assertTrue(updatedBooking.get("depositpaid").asBoolean());
        assertNotNull(updatedBooking.get("bookingdates"));
        assertEquals(updatedBooking.get("additionalneeds").asText(), "Breakfast");
    }

    @Test
    public void deleteBooking() {
        int bookingId = 1;
        APIResponse response = api.delete("/booking/" + bookingId);
        assertEquals(response.statusText(), "Created");
    }
}
