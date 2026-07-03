package com.automation.apiTests;

import com.automation.utils.ApiUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookingApiTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingApiTest.class);
    private static final String BASE_URL = System.getProperty("apiBaseUrl", "https://restful-booker.herokuapp.com");
    private static final String API_TOKEN = System.getProperty("apiToken", "");

    private ApiUtils api;
    private APIRequestContext requestContext;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        Map<String, String> headers = new HashMap<>();
        if (!API_TOKEN.isEmpty()) {
            headers.put("Authorization", "Bearer " + API_TOKEN);
        }
        api = new ApiUtils(BASE_URL, headers);
        requestContext = api.getRequestContext();
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
        body.put("bookingdates", Map.of("checkin", "2023-05-01", "checkout", "2023-05-05"));
        body.put("additionalneeds", "Breakfast");

        APIRequest request = requestContext.post("/booking", APIRequest.bodyFromJson(body));
        APIResponse response = request.raise();

        try {
            assertEquals(response.status(), 200, "Failed to create a new booking");
            JsonNode responseBody = response.json();
            assertTrue(responseBody.has("bookingid"), "Response does not contain a booking ID");
            logger.info("New booking created with ID: {}", responseBody.get("bookingid").asText());
        } catch (Exception e) {
            logger.error("Error occurred during API request", e);
        }
    }
}
