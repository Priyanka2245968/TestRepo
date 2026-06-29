package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaArticlePage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticlePage(testManager);
    }

    @Given("I navigate to Wikipedia")
    public void iNavigateToWikipedia() {
        pageObject.navigateToWikipedia();
    }

    @When("I search for {string}")
    public void iSearchFor(String term) {
        pageObject.searchForTerm(term);
    }

    @When("I view the article")
    public void iViewTheArticle() {
        pageObject.viewArticle();
    }

    @Then("the article {string} should be displayed")
    public void theArticleShouldBeDisplayed(String title) {
        assertThat(testManager.getPage()).hasURL("https://en.wikipedia.org/wiki/" + title);
        assertThat(testManager.getPage()).hasTitle(title + " - Wikipedia");
        pageObject.takeScreenshot(title.replace(" ", "_") + "_article.png");
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}