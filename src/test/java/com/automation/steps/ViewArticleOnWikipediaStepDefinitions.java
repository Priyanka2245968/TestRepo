package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class ViewArticleOnWikipediaStepDefinitions {

    private BaseTestManager testManager;
    private ViewArticleOnWikipediaPage pageObject;

    @Before
    public void setUp() {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new ViewArticleOnWikipediaPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        testManager.getPage().navigate(url);
    }

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNumber, String description) {
        Map<Integer, Runnable> steps = Map.ofEntries(
                Map.entry(1, () -> pageObject.navigateToWikipediaHomepage()),
                Map.entry(2, () -> pageObject.searchForTerm("Python programming language")),
                Map.entry(3, pageObject::clickSearchButton),
                Map.entry(4, pageObject::waitForSearchResultsToLoad),
                Map.entry(5, pageObject::clickPythonProgrammingLanguageLink),
                Map.entry(6, pageObject::waitForArticleToLoad),
                Map.entry(7, pageObject::verifyArticleContentVisible)
        );

        steps.getOrDefault(stepNumber, () -> { throw new IllegalArgumentException("Invalid step number"); }).run();
    }

    @Then("I should see the {string} page")
    public void iShouldSeeThePage(String pageName) {
        switch (pageName) {
            case "no results" -> pageObject.verifyNoResultsPageVisible();
            case "article" -> pageObject.verifyArticleContentVisible();
            default -> throw new IllegalArgumentException("Invalid page name");
        }
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}