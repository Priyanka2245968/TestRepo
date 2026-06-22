package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.automation.utils.WaitUtils.waitForElementToBeVisible;

public class WikipediaArticleStepDefinitions {
    private static final Logger logger = LogManager.getLogger(WikipediaArticleStepDefinitions.class);
    private BaseTestManager testManager;
    private WikipediaArticlePage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticlePage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        pageObject.navigateToWikipedia(url);
    }

    @When("I execute step {string}: {string}")
    public void executeStep(String stepNumber, String description) throws Exception {
        switch (stepNumber) {
            case "1":
                pageObject.searchForArticle(description);
                break;
            case "2":
                pageObject.openArticle(description);
                break;
            case "3":
                boolean isArticleDisplayed = pageObject.isArticleDisplayed(description);
                logger.info("Article '" + description + "' is displayed: " + isArticleDisplayed);
                break;
            default:
                logger.warn("Unknown step number: " + stepNumber);
                throw new IllegalArgumentException("Invalid step number: " + stepNumber);
        }
    }

    @Then("I verify that the article {string} is displayed")
    public void verifyArticleIsDisplayed(String articleTitle) {
        boolean isArticleDisplayed = pageObject.isArticleDisplayed(articleTitle);
        if (!isArticleDisplayed) {
            logger.error("Article '" + articleTitle + "' is not displayed");
        }
        waitForElementToBeVisible(pageObject.getPage().locator("//h1[contains(., '" + articleTitle + "')]"));
    }

    @After
    public void tearDown() {
        testManager.quitBrowser();
    }
}