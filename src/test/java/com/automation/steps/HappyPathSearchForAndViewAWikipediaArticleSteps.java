package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class HappyPathSearchForAndViewAWikipediaArticleSteps {
    private WebDriver driver;
    private HappyPathSearchForAndViewAWikipediaArticlePage page;

    public HappyPathSearchForAndViewAWikipediaArticleSteps() {
        // driver initialisation happens via hooks
    }

    @Given("^Navigate to https://www.wikipedia.org/$")
    public void Navigate_to_https___www_wikipedia_org() throws Throwable {
        // TODO: implement step
    }

    @Given("^In the 'Search Wikipedia' field, enter 'Python programming language'$")
    public void In_the__Search_Wikipedia__field__enter() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the 'Search' icon or press Enter$")
    public void Click_the__Search__icon_or_press_Enter() throws Throwable {
        // TODO: implement step
    }

    @Given("^From the search results, click the 'Python (programming language)' link$")
    public void From_the_search_results__click_the__Pyth() throws Throwable {
        // TODO: implement step
    }

    @Given("^Verify the 'Python (programming language)' article page has loaded$")
    public void Verify_the__Python__programming_language() throws Throwable {
        // TODO: implement step
    }
}