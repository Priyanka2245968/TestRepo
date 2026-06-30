package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CreateBookingApiStepDefinitions extends BaseTestManager {
    private ObjectMapper objectMapper;
    private JsonNode response;

    public CreateBookingApiStepDefinitions() {
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() {
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        // No action needed for API tests
    }

    @When("I send a POST request to {string} with the following payload:")
    public void sendPostRequest(String endpoint, DataTable dataTable) throws IOException {
        Map<String, Object> payload = new HashMap<>();
        for (Map<String, String> row : dataTable.asMaps()) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.contains(".")) {
                    String[] parts = key.split("\\.");
                    Map<String, Object> nestedMap = (Map<String, Object>) payload.getOrDefault(parts[0], new HashMap<>());
                    nestedMap.put(parts[1], value);
                    payload.put(parts[0], nestedMap);
                } else {
                    payload.put(key, value);
                }
            }
        }
        response = new ObjectMapper().readTree(apiContext.post("https://restful-booker.herokuapp.com" + endpoint, payload).text());
    }

    @Then("the response should have a {string} field")
    public void theResponseShouldHaveAField(String field) {
        assertNotNull(response);
        assertTrue(response.has(field));
    }

    @Then("the response {string} field should be {string}")
    public void theResponseFieldShouldBe(String field, String expectedValue) {
        assertNotNull(response);
        assertEquals(response.get(field).asText(), expectedValue);
    }

    @Then("the response {string} field should be {int}")
    public void theResponseFieldShouldBe(String field, int expectedValue) {
        assertNotNull(response);
        assertEquals(response.get(field).asInt(), expectedValue);
    }

    @Then("the response {string} field should be {boolean}")
    public void theResponseFieldShouldBe(String field, boolean expectedValue) {
        assertNotNull(response);
        assertEquals(response.get(field).asBoolean(), expectedValue);
    }

    @Then("the response {string} field should contain {string}")
    public void theResponseFieldShouldContain(String field, String expectedValue) {
        assertNotNull(response);
        assertTrue(response.get(field).asText().contains(expectedValue));
    }
}