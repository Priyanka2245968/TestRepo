package wikipedia;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import wikipedia.pages.HomePage;
import wikipedia.pages.SearchResultsPage;

public class ViewArticleSteps {

  private WebDriver driver;
  private HomePage homePage;
  private SearchResultsPage searchResultsPage;

  public ViewArticleSteps() {
    this.driver = DriverFactory.getDriver();
    this.homePage = PageFactory.initElements(driver, HomePage.class);
    this.searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
  }

  @Given("I navigate to {string}")
  public void navigateTo(String url) {
    driver.get(url);
  }

  @When("I search for {string}")
  public void searchFor(String term) {
    homePage.searchFor(term);
  }

  @When("I click on the {string} link")
  public void clickLink(String linkText) {
    searchResultsPage.clickLink(linkText);
  }

  @Then("I should see the {string} article page")
  public void verifyArticlePage(String title) {
    assertTrue("Article title does not match", driver.getTitle().contains(title));
  }

  @Then("I should see {string}")
  public void verifyMessage(String message) {
    assertTrue("Expected message not found", searchResultsPage.getErrorMessage().contains(message));
  }
}