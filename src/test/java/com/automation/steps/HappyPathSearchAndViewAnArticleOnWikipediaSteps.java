package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class HappyPathSearchAndViewAnArticleOnWikipediaSteps {
    private WebDriver driver;
    private HappyPathSearchAndViewAnArticleOnWikipediaPage page;

    public HappyPathSearchAndViewAnArticleOnWikipediaSteps() {
        // driver initialisation happens via hooks
    }

    @Given("^Navigate to Wikipedia homepage$")
    public void Navigate_to_Wikipedia_homepage() throws Throwable {
        // TODO: implement step
    }

    @Given("^Enter 'HTML' in the search box$")
    public void Enter__HTML__in_the_search_box() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the Wikipedia search button$")
    public void Click_the_Wikipedia_search_button() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the link for the 'HTML' article in the search results$")
    public void Click_the_link_for_the__HTML__article_in() throws Throwable {
        // TODO: implement step
    }
}