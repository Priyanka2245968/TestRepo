package com.automation.steps;

import com.automation.pages.WikipediaArticlePage;
import com.automation.base.BaseTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticleSteps extends BaseTestManager {
    private WikipediaArticlePage wikipediaArticlePage;

    @Given("I navigate to Wikipedia homepage")
    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org/");
        wikipediaArticlePage = new WikipediaArticlePage(this);
    }

    @When("I search for {string}")
    public void searchForArticle(String searchTerm) {
        wikipediaArticlePage.enterSearchText(searchTerm);
        wikipediaArticlePage.clickSearchButton();
    }

    @When("I click on the {string} link")
    public void clickArticleLink(String linkText) {
        if (linkText.equals("HTML")) {
            wikipediaArticlePage.clickHtmlLink();
        }
    }

    @Then("I should see the {string} article page")
    public void verifyArticlePage(String articleTitle) {
        assertThat(page).hasTitle(articleTitle);
    }

    @Then("I should see an error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = page.locator(".error-message").textContent();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Then("I should see search results for the truncated search")
    public void verifyTruncatedSearchResults() {
        String truncatedSearchTerm = "This is a very long string with exactly 300 characters. This is a very long string with exactly 300 characters. This is a very long string with exactly 300 characters. This is a very long string with exactly 300 characters. This is a very long string with exactly 300 char";
        assertThat(page).hasURL("https://www.wikipedia.org/wiki/Special:Search?search=" + truncatedSearchTerm);
    }
}