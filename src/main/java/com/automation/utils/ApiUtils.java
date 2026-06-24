package com.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiUtils - Reusable REST API helper built on Playwright's native {@link APIRequestContext}.
 *
 * <p>This utility intentionally does NOT launch a browser. It manages its own lightweight
 * Playwright + APIRequestContext lifecycle so that pure API suites stay fast and headless.</p>
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 *   ApiUtils api = new ApiUtils("https://jsonplaceholder.typicode.com");
 *   try {
 *       APIResponse response = api.get("/posts/1");
 *       JsonNode body = api.asJson(response);
 *   } finally {
 *       api.dispose();
 *   }
 * }</pre>
 */
public class ApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(ApiUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final Playwright playwright;
    private final APIRequestContext requestContext;
    private final String baseUrl;

    /**
     * Creates an API client bound to the given base URL with sensible JSON defaults.
     *
     * @param baseUrl the base URL for all requests (e.g. https://api.example.com)
     */
    public ApiUtils(String baseUrl) {
        this(baseUrl, defaultHeaders());
    }

    /**
     * Creates an API client bound to the given base URL with custom default headers.
     *
     * @param baseUrl        the base URL for all requests
     * @param defaultHeaders headers applied to every request (e.g. Authorization)
     */
    public ApiUtils(String baseUrl, Map<String, String> defaultHeaders) {
        this.baseUrl = baseUrl;
        this.playwright = Playwright.create();
        this.requestContext = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL(baseUrl)
                        .setExtraHTTPHeaders(defaultHeaders)
                        // Tolerate corporate proxy / self-signed certificate chains (MITM TLS).
                        // Override with -DignoreHttpsErrors=false in trusted environments.
                        .setIgnoreHTTPSErrors(
                                Boolean.parseBoolean(System.getProperty("ignoreHttpsErrors", "true")))
                        .setTimeout(30_000));
        logger.info("ApiUtils initialized against base URL: {}", baseUrl);
    }

    /** @return default JSON content/accept headers. */
    private static Map<String, String> defaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return headers;
    }

    /**
     * Performs an HTTP GET.
     *
     * @param endpoint path appended to the base URL (e.g. "/posts/1")
     * @return the raw {@link APIResponse}
     */
    public APIResponse get(String endpoint) {
        return get(endpoint, null);
    }

    /**
     * Performs an HTTP GET with optional query parameters.
     *
     * @param endpoint    path appended to the base URL
     * @param queryParams query parameters (may be {@code null})
     * @return the raw {@link APIResponse}
     */
    public APIResponse get(String endpoint, Map<String, String> queryParams) {
        logger.info("GET {}{} params={}", baseUrl, endpoint, queryParams);
        RequestOptions options = RequestOptions.create();
        if (queryParams != null) {
            queryParams.forEach(options::setQueryParam);
        }
        APIResponse response = requestContext.get(endpoint, options);
        logResponse("GET", endpoint, response);
        return response;
    }

    /**
     * Performs an HTTP POST with a JSON body.
     *
     * @param endpoint path appended to the base URL
     * @param body     a POJO/Map serialized to JSON, or a raw JSON string
     * @return the raw {@link APIResponse}
     */
    public APIResponse post(String endpoint, Object body) {
        logger.info("POST {}{}", baseUrl, endpoint);
        APIResponse response = requestContext.post(endpoint,
                RequestOptions.create().setData(body));
        logResponse("POST", endpoint, response);
        return response;
    }

    /**
     * Performs an HTTP PUT with a JSON body.
     *
     * @param endpoint path appended to the base URL
     * @param body     a POJO/Map serialized to JSON, or a raw JSON string
     * @return the raw {@link APIResponse}
     */
    public APIResponse put(String endpoint, Object body) {
        logger.info("PUT {}{}", baseUrl, endpoint);
        APIResponse response = requestContext.put(endpoint,
                RequestOptions.create().setData(body));
        logResponse("PUT", endpoint, response);
        return response;
    }

    /**
     * Performs an HTTP DELETE.
     *
     * @param endpoint path appended to the base URL
     * @return the raw {@link APIResponse}
     */
    public APIResponse delete(String endpoint) {
        logger.info("DELETE {}{}", baseUrl, endpoint);
        APIResponse response = requestContext.delete(endpoint);
        logResponse("DELETE", endpoint, response);
        return response;
    }

    /**
     * Parses a response body into a Jackson {@link JsonNode} tree.
     *
     * @param response the API response
     * @return the parsed JSON tree
     */
    public JsonNode asJson(APIResponse response) {
        try {
            return MAPPER.readTree(response.body());
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response body as JSON: " + response.text(), e);
        }
    }

    /**
     * Serializes any object to a JSON string. Useful for building request payloads.
     *
     * @param object the object to serialize
     * @return the JSON string
     */
    public String toJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    private void logResponse(String method, String endpoint, APIResponse response) {
        logger.info("{} {} -> {} {}", method, endpoint, response.status(), response.statusText());
    }

    /**
     * Releases the underlying APIRequestContext and Playwright instance.
     * Always call this (e.g. from an @AfterClass hook) to avoid resource leaks.
     */
    public void dispose() {
        try {
            if (requestContext != null) {
                requestContext.dispose();
            }
        } finally {
            if (playwright != null) {
                playwright.close();
            }
        }
        logger.info("ApiUtils disposed");
    }
}
