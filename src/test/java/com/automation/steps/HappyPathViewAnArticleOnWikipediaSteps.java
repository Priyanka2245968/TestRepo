package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class HappyPathViewAnArticleOnWikipediaSteps {
    private WebDriver driver;
    private HappyPathViewAnArticleOnWikipediaPage page;

    public HappyPathViewAnArticleOnWikipediaSteps() {
        // driver initialisation happens via hooks
    }

    @Given("^Navigate to Wikipedia homepage$")
    public void Navigate_to_Wikipedia_homepage() throws Throwable {
        // TODO: implement step
    }

    @Given("^Enter 'Photosynthesis' in the search box$")
    public void Enter__Photosynthesis__in_the_search_box() throws Throwable {
        // TODO: implement step
    }

    @Given("^Press Enter to search$")
    public void Press_Enter_to_search() throws Throwable {
        // TODO: implement step
    }

    @Given("^Verify search results list an article titled 'Photosynthesis'$")
    public void Verify_search_results_list_an_article_ti() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the 'Photosynthesis' link in search results$")
    public void Click_the__Photosynthesis__link_in_searc() throws Throwable {
        // TODO: implement step
    }
}