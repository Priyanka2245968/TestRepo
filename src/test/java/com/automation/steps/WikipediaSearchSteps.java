package com.automation.steps;

import com.automation.pages.WikipediaSearchPage;
import com.automation.base.BaseTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class WikipediaSearchSteps {
    private final BaseTestManager testManager;
    private final WikipediaSearchPage wikipediaPage;

    public WikipediaSearchSteps(BaseTestManager testManager) {
        this.testManager = testManager;
        this.wikipediaPage = new WikipediaSearchPage(testManager);
    }

    @Given("I navigate to Wikipedia")
    public void navigateToWikipedia() {
        wikipediaPage.navigateToWikipedia();
    }

    @When("I search for {string}")
    public void searchForTerm(String term) {
        wikipediaPage.searchForTerm(term);
    }

    @When("I click the Python link")
    public void clickPythonLink() {
        wikipediaPage.clickPythonLink();
    }

    @Then("I should see the Python article page")
    public void verifyPythonArticlePage() {
        PlaywrightAssertions.assertThat(testManager.getPage()).hasTitle("Python (programming language) - Wikipedia");
    }
}