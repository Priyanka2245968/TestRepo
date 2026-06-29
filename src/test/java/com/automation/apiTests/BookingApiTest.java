package com.automation.apiTests;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

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
        payload.put("bookingdates", Map.of("checkin", "2023-06-01", "checkout", "2023-06-05"));
        payload.put("additionalneeds", "Breakfast");

        APIRequest request = apiContext.post(BASE_URL + "/booking", playwright -> playwright.data(objectMapper.writeValueAsString(payload)));
        APIResponse response = request.get();
        int statusCode = response.statusCode();
        JsonNode responseBody = objectMapper.readTree(response.body());

        Assert.assertEquals(statusCode, 200, "API response status code is not 200 OK");
        Assert.assertNotNull(responseBody.get("bookingid"), "Booking ID is null in the response");
    }
}
