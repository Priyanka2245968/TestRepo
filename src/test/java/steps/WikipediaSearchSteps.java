package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.WikipediaSearchPage;
import utils.Driver;

import java.time.Duration;

public class WikipediaSearchSteps {
    WikipediaSearchPage searchPage = new WikipediaSearchPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @Given("I am on the Wikipedia homepage")
    public void navigateToWikipedia() {
        Driver.getDriver().get("https://www.wikipedia.org");
    }

    @When("I enter {string} in the search field")
    public void enterSearchTerm(String term) {
        searchPage.searchInput.sendKeys(term);
    }

    @And("I click the search button")
    public void clickSearchButton() {
        searchPage.searchButton.click();
    }

    @Then("I should see the search results for {string}")
    public void verifySearchResults(String term) {
        String expectedTitle = "Search results for: " + term;
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedTitle));
    }

    @And("I click the {string} link")
    public void clickLink(String linkText) {
        if (linkText.equals("HTML")) {
            searchPage.htmlLink.click();
        }
    }

    @Then("I should see the {string} page")
    public void verifyPage(String pageName) {
        if (pageName.equals("HTML")) {
            // Add assertions to verify the content of the HTML page
        }
    }

    @Then("I should see an error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
        // Add implementation to verify the error message
    }
}