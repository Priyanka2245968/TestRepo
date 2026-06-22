package com.automation.steps;

import com.automation.pages.WikipediaArticlePage;
import com.automation.base.BaseTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class WikipediaSteps extends BaseTestManager {
    private WikipediaArticlePage wikipediaPage;

    @Given("I navigate to Wikipedia")
    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        wikipediaPage = new WikipediaArticlePage(this);
    }

    @When("I search for {string}")
    public void searchWikipedia(String query) {
        wikipediaPage.searchWikipedia(query);
    }

    @When("I click on the Python Programming Language link")
    public void clickPythonProgrammingLanguageLink() {
        wikipediaPage.clickPythonProgrammingLanguageLink();
    }

    @Then("I should see the Python Programming Language article")
    public void verifyPythonProgrammingLanguageArticle() {
        assertTrue(wikipediaPage.getPageUrl().contains("Python_(programming_language)"));
        assertTrue(wikipediaPage.getPageContent().contains("Python is an interpreted high-level general-purpose programming language"));
    }
}