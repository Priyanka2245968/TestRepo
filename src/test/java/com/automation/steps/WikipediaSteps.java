package com.automation.steps;

import com.automation.pages.WikipediaArticlePage;
import com.automation.base.BaseTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaSteps {
    private final BaseTestManager testManager;
    private final WikipediaArticlePage wikipediaPage;

    public WikipediaSteps(BaseTestManager testManager) {
        this.testManager = testManager;
        this.wikipediaPage = new WikipediaArticlePage(testManager);
    }

    @Given("I navigate to {string}")
    public void navigateToUrl(String url) {
        testManager.getPage().navigate(url);
    }

    @When("I search for {string} on Wikipedia")
    public void searchWikipedia(String query) {
        wikipediaPage.searchWikipedia(query);
    }

    @When("I click the HTML table link")
    public void clickHtmlTableLink() {
        wikipediaPage.clickHtmlTableLink();
    }

    @Then("I should see the HTML table article page")
    public void verifyHtmlTableArticlePage() {
        assertThat(testManager.getPage()).hasTitle("HTML table - Wikipedia");
    }
}