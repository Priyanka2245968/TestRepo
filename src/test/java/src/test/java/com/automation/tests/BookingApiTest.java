package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class BookingApiTest {
    private APIRequestContext apiContext;
    private String baseUrl;
    private String apiToken;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        baseUrl = System.getProperty("apiBaseUrl", "https://restful-booker.herokuapp.com");
        apiToken = System.getProperty("apiToken", "");

        playwright = Playwright.create();
        apiContext = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(baseUrl)
                .setExtraHTTPHeaders(Map.of("Authorization", "Bearer " + apiToken)));
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        apiContext.dispose();
        playwright.close();
    }

    @Test
    public void createValidBooking() throws Exception {
        // Arrange
        String body = "{\"firstname\":\"Swarup\",\"lastname\":\"Roy\",\"totalprice\":12000,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2026-07-10\",\"checkout\":\"2026-07-12\"},\"additionalneeds\":\"Breakfast\"}";
        RequestOptions options = new RequestOptions().setData(body.getBytes()).setHeaders(Map.of("Content-Type", "application/json"));

        // Act
        APIResponse response = apiContext.post("/booking", options);

        // Assert
        Assert.assertEquals(response.status(), 200, "Response status is not 200 OK");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.text());
        Assert.assertTrue(json.has("bookingid"), "Response does not contain 'bookingid' field");
        Assert.assertNotEquals(json.get("bookingid").asInt(), 0, "'bookingid' value is 0 or empty");
    }
}
