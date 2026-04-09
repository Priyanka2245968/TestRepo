package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class WikipediaSearchFunctionalitySteps {
    private WebDriver driver;
    private WikipediaSearchFunctionalityPage page;

    public WikipediaSearchFunctionalitySteps() {
        // driver initialisation happens via hooks
    }

    @Given("^1. Navigate to the Wikipedia homepage$")
    public void 1__Navigate_to_the_Wikipedia_homepage() throws Throwable {
        // TODO: implement step
    }

    @Given("^2. Enter the search term "Artificial Intelligence" in the search field$")
    public void 2__Enter_the_search_term__Artificial_Int() throws Throwable {
        // TODO: implement step
    }

    @Given("^3. Click the Search button$")
    public void 3__Click_the_Search_button() throws Throwable {
        // TODO: implement step
    }
}