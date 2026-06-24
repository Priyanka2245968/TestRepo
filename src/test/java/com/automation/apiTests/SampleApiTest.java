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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Sample REST API test suite for the JSONPlaceholder service.
 *
 * <p>Demonstrates the framework's API testing capability using the reusable
 * {@link ApiUtils} helper (Playwright {@code APIRequestContext}). Covers the
 * core HTTP verbs — GET, POST, PUT, DELETE — with status-code, payload and
 * schema-level assertions.</p>
 *
 * <p>Runs via the standard TestNG suite (package {@code com.automation.tests}
 * is scanned by {@code src/test/resources/testng.xml}).</p>
 */
public class SampleApiTest {

    private static final Logger logger = LoggerFactory.getLogger(SampleApiTest.class);
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private ApiUtils api;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        logger.info("Setting up API client for base URL: {}", BASE_URL);
        api = new ApiUtils(BASE_URL);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (api != null) {
            api.dispose();
        }
    }

    @Test(description = "GET /posts/1 should return a single post with the expected schema")
    public void getSinglePost_returnsExpectedPost() {
        APIResponse response = api.get("/posts/1");

        assertTrue(response.ok(), "Expected a successful (2xx) response");
        assertEquals(response.status(), 200, "Unexpected HTTP status code");

        JsonNode post = api.asJson(response);
        assertEquals(post.get("id").asInt(), 1, "Returned post id mismatch");
        assertEquals(post.get("userId").asInt(), 1, "Returned userId mismatch");
        assertTrue(post.hasNonNull("title"), "Response should contain a 'title' field");
        assertFalse(post.get("title").asText().isBlank(), "'title' should not be blank");
        assertTrue(post.hasNonNull("body"), "Response should contain a 'body' field");
    }

    @Test(description = "GET /posts with a userId query parameter should return only that user's posts")
    public void getPostsByUser_returnsFilteredList() {
        Map<String, String> query = new HashMap<>();
        query.put("userId", "1");

        APIResponse response = api.get("/posts", query);

        assertEquals(response.status(), 200, "Unexpected HTTP status code");

        JsonNode posts = api.asJson(response);
        assertTrue(posts.isArray(), "Response should be a JSON array");
        assertTrue(posts.size() > 0, "Expected at least one post for userId=1");
        posts.forEach(post ->
                assertEquals(post.get("userId").asInt(), 1, "Every post should belong to userId=1"));
    }

    @Test(description = "POST /posts should create a resource and echo back the payload with a new id")
    public void createPost_returnsCreatedResource() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Automated API Test");
        payload.put("body", "Created via Playwright APIRequestContext");
        payload.put("userId", 1);

        APIResponse response = api.post("/posts", payload);

        assertEquals(response.status(), 201, "POST should return 201 Created");

        JsonNode created = api.asJson(response);
        assertNotNull(created.get("id"), "Created resource should have an id");
        assertEquals(created.get("title").asText(), "Automated API Test", "Title not echoed back");
        assertEquals(created.get("userId").asInt(), 1, "userId not echoed back");
    }

    @Test(description = "PUT /posts/1 should update an existing resource")
    public void updatePost_returnsUpdatedResource() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", 1);
        payload.put("title", "Updated Title");
        payload.put("body", "Updated via PUT");
        payload.put("userId", 1);

        APIResponse response = api.put("/posts/1", payload);

        assertEquals(response.status(), 200, "PUT should return 200 OK");

        JsonNode updated = api.asJson(response);
        assertEquals(updated.get("title").asText(), "Updated Title", "Title was not updated");
        assertEquals(updated.get("id").asInt(), 1, "id should remain unchanged");
    }

    @Test(description = "DELETE /posts/1 should succeed")
    public void deletePost_succeeds() {
        APIResponse response = api.delete("/posts/1");

        assertTrue(response.ok(), "DELETE should return a successful status code");
        assertEquals(response.status(), 200, "DELETE should return 200 OK");
    }
}
