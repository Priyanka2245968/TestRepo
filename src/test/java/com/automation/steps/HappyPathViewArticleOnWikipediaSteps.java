package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class HappyPathViewArticleOnWikipediaSteps {
    private WebDriver driver;
    private HappyPathViewArticleOnWikipediaPage page;

    public HappyPathViewArticleOnWikipediaSteps() {
        // driver initialisation happens via hooks
    }

    @Given("^Navigate to https://www.wikipedia.org/$")
    public void Navigate_to_https___www_wikipedia_org() throws Throwable {
        // TODO: implement step
    }

    @Given("^In the 'Search Wikipedia' field, enter 'Photosynthesis'$")
    public void In_the__Search_Wikipedia__field__enter() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the 'Search' icon button$")
    public void Click_the__Search__icon_button() throws Throwable {
        // TODO: implement step
    }

    @Given("^Verify the search results page loads$")
    public void Verify_the_search_results_page_loads() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the 'Photosynthesis' link in the search results$")
    public void Click_the__Photosynthesis__link_in_the_s() throws Throwable {
        // TODO: implement step
    }
}