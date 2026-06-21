package wikipedia;

import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewArticleSteps {
  @Given("I navigate to {string}")
  public void navigateTo(String url) {
    // Navigate to Wikipedia homepage
  }

  @When("I search for {string}")
  public void searchFor(String term) {
    // Enter search term and submit
  }

  @When("I click on the {string} link")
  public void clickLink(String linkText) {
    // Click link with matching text
  }

  @Then("I should see the {string} article page")
  public void verifyArticlePage(String title) {
    // Assert article page loaded with title
  }

  @Then("I should see {string}")
  public void verifyMessage(String message) {
    // Assert error/info message is displayed
  }
}