package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class HappyPathViewWikipediaArticleForATopicSearchSteps {
    private WebDriver driver;
    private HappyPathViewWikipediaArticleForATopicSearchPage page;

    public HappyPathViewWikipediaArticleForATopicSearchSteps() {
        // driver initialisation happens via hooks
    }

    @Given("^Navigate to Wikipedia homepage$")
    public void Navigate_to_Wikipedia_homepage() throws Throwable {
        // TODO: implement step
    }

    @Given("^Enter 'HTML Tables' in the search box$")
    public void Enter__HTML_Tables__in_the_search_box() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the Search button$")
    public void Click_the_Search_button() throws Throwable {
        // TODO: implement step
    }

    @Given("^Wait for search results to load$")
    public void Wait_for_search_results_to_load() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the top search result 'HTML Table Element'$")
    public void Click_the_top_search_result__HTML_Table() throws Throwable {
        // TODO: implement step
    }
}