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
    public void positiveCreateSuccessfulBookingWithAllFields() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Swarup");
        body.put("lastname", "Roy");
        body.put("totalprice", 12000);
        body.put("depositpaid", true);
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-05-01");
        bookingDates.put("checkout", "2023-05-05");
        body.put("bookingdates", bookingDates);

        APIResponse response = api.post("/booking", body);
        JsonNode responseBody = api.asJson(response);
        org.testng.Assert.assertTrue(response.statusText().contains("OK"));
        org.testng.Assert.assertTrue(responseBody.has("bookingid"));
        org.testng.Assert.assertTrue(responseBody.get("booking").has("firstname"));
        org.testng.Assert.assertEquals(responseBody.get("booking").get("firstname").asText(), "Swarup");
    }

    // Add negative test cases for invalid inputs or error scenarios
}
