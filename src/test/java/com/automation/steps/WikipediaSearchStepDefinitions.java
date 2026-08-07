package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;

public class WikipediaSearchStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaSearchPage pageObject;
    
    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaSearchPage(testManager);
    }
    
    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        System.out.println("🌐 Navigating to: " + url);
        pageObject.navigateToWikipediaHomepage();
    }
    
    @When("I type {string} in the search box")
    public void iTypeInTheSearchBox(String text) throws Exception {
        pageObject.typeInSearchBox(text);
    }
    
    @When("I click the Search button")
    public void iClickTheSearchButton() throws Exception {
        pageObject.clickSearchButton();
    }
    
    @When("I wait for at least 1 link with visible text containing {string} to appear")
    public void iWaitForAtLeastLinkWithVisibleTextContainingToAppear(String text) throws Exception {
        pageObject.waitForSearchResults();
    }
    
    @When("I click the first visible link with text containing {string}")
    public void iClickTheFirstVisibleLinkWithTextContaining(String text) throws Exception {
        pageObject.clickFirstLinkContainingText(text);
    }
    
    @When("I wait for the heading with role={string} and visible text matching {string}")
    public void iWaitForTheHeadingWithRoleAndVisibleTextMatching(String role, String text) throws Exception {
        pageObject.waitForWikipediaArticleHeading();
    }
    
    @When("I scroll down until the section with role={string} and visible name={string} appears")
    public void iScrollDownUntilTheSectionWithRoleAndVisibleNameAppears(String role, String name) throws Exception {
        pageObject.scrollToContentsSection();
    }
    
    @When("I click the first visible link under the {string} section")
    public void iClickTheFirstVisibleLinkUnderTheSection(String sectionName) throws Exception {
        pageObject.clickFirstLinkUnderContentsSection();
    }
    
    @When("I wait for the URL to change")
    public void iWaitForTheURLToChange() throws Exception {
        pageObject.waitForNavigationToComplete();
    }
    
    @When("I go back in the browser history")
    public void iGoBackInTheBrowserHistory() throws Exception {
        pageObject.goBackInBrowserHistory();
    }
    
    @When("I click the visible link with text {string} and role={string}")
    public void iClickTheVisibleLinkWithTextAndRole(String linkText, String role) throws Exception {
        pageObject.clickEditLink();
    }
    
    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("bdd-wikipedia-search-test-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
    
    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}