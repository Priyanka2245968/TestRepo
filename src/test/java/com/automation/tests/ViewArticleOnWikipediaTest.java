package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;
import com.microsoft.playwright.options.LoadState;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    private WikipediaSearchPage wikipediaPage;

    @Given("I am on the Wikipedia homepage")
    public void navigateToWikipedia() {
        wikipediaPage = new WikipediaSearchPage(this);
        wikipediaPage.navigateToWikipedia();
    }

    @When("I search for {string}")
    public void searchForTopic(String topic) {
        wikipediaPage.searchForTopic(topic);
        wikipediaPage.waitForSearchResults();
    }

    @When("I click on the search result {string}")
    public void clickSearchResult(String resultLink) {
        wikipediaPage.clickSearchResult(resultLink);
        wikipediaPage.waitForArticleLoad();
    }

    @Then("I should see the article titled {string}")
    public void verifyArticleTitle(String expectedTitle) {
        assertThat(wikipediaPage.getArticleTitleLocator()).containsText(expectedTitle);
        wikipediaPage.takeScreenshot("python-article.png");
    }

    @Then("I should see a message indicating no results found")
    public void verifyNoResultsMessage() {
        String noResultsMessage = wikipediaPage.getNoResultsMessage();
        Assert.assertTrue(noResultsMessage.contains("No results found"), "Expected 'No results found' message not displayed");
    }
}