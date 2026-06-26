package com.automation.apiTests;

import com.automation.utils.ApiUtils;
import com.microsoft.playwright.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaApiTest {
    private static final Logger logger = LoggerFactory.getLogger(ViewArticleOnWikipediaApiTest.class);
    private static final String BASE_URL = "https://www.wikipedia.org";
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

    @Test(description = "GET / should return Wikipedia homepage")
    public void getHomepage_returnsSuccessfully() {
        APIResponse response = api.get("/");
        assertEquals(response.statusText(), "OK");
        assertTrue(new String(response.body()).contains("Search Wikipedia"));
    }

    @Test(description = "GET /?search=Python+programming+language should return search results")
    public void searchForTopic_returnsResults() {
        APIResponse response = api.get("/?search=Python+programming+language");
        assertEquals(response.statusText(), "OK");
        assertTrue(new String(response.body()).contains("Python (programming language)"));
    }
}