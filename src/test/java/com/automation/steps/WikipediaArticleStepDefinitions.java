package com.automation.steps;

import com.automation.pages.WikipediaArticlePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

public class WikipediaArticleStepDefinitions {
    private WebDriver driver;
    private WikipediaArticlePage pageObject;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        pageObject = new WikipediaArticlePage(driver);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        pageObject.navigateToWikipedia(url);
    }

    @When("I search for {string}")
    public void iSearchFor(String query) {
        pageObject.searchWikipedia(query);
    }

    @When("I click the search result {string}")
    public void iClickTheSearchResult(String title) {
        pageObject.clickSearchResult(title);
    }

    @Then("I should see the {string} article")
    public void iShouldSeeTheArticle(String title) {
        pageObject.verifyArticleTitle(title);
        assertTrue(driver.getTitle().contains(title));
        pageObject.takeScreenshot("article_" + title.replaceAll("\\s+", "_").toLowerCase());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}