package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.WikipediaSearchPage;

import static org.junit.Assert.assertTrue;

public class WikipediaSearchSteps {
    private WebDriver driver;
    private WikipediaSearchPage searchPage;

    public WikipediaSearchSteps(WebDriver driver) {
        this.driver = driver;
        searchPage = new WikipediaSearchPage(driver);
    }

    @Given("I am on the Wikipedia homepage")
    public void navigateToWikipedia() {
        driver.get("https://www.wikipedia.org/");
    }

    @When("I search for {string}")
    public void searchForTerm(String term) {
        searchPage.enterSearchText(term);
        searchPage.clickSearchButton();
    }

    @Then("I should see the search results page")
    public void verifySearchResultsPage() {
        assertTrue(searchPage.isSearchResultsDisplayed());
    }

    @When("I click the HTML Tutorial link")
    public void clickHtmlTutorialLink() {
        searchPage.clickHtmlTutorialLink();
    }

    @Then("I should see the HTML Tutorial page")
    public void verifyHtmlTutorialPage() {
        assertTrue(searchPage.isHtmlTutorialPageDisplayed());
    }
}