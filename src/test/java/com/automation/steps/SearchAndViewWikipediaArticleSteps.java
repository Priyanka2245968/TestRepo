package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class SearchAndViewWikipediaArticleSteps {
    private WebDriver driver;
    private SearchAndViewWikipediaArticlePage page;

    public SearchAndViewWikipediaArticleSteps() {
        // driver initialisation happens via hooks
    }

    @Given("^Navigate to https://www.wikipedia.org/$")
    public void Navigate_to_https___www_wikipedia_org() throws Throwable {
        // TODO: implement step
    }

    @Given("^Enter 'Python programming' in the search bar$")
    public void Enter__Python_programming__in_the_search() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the search button or press Enter$")
    public void Click_the_search_button_or_press_Enter() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the top search result titled 'Python (programming language)'$")
    public void Click_the_top_search_result_titled__Pyth() throws Throwable {
        // TODO: implement step
    }
}