package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class HappyPathViewAnArticleOnWikipediaBySearchingForATopicSteps {
    private WebDriver driver;
    private HappyPathViewAnArticleOnWikipediaBySearchingForATopicPage page;

    public HappyPathViewAnArticleOnWikipediaBySearchingForATopicSteps() {
        // driver initialisation happens via hooks
    }

    @Given("^Navigate to Wikipedia homepage$")
    public void Navigate_to_Wikipedia_homepage() throws Throwable {
        // TODO: implement step
    }

    @Given("^Enter 'HTML Tutorial' in the search box$")
    public void Enter__HTML_Tutorial__in_the_search_box() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the Search button next to the search field$")
    public void Click_the_Search_button_next_to_the_sear() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the 'HTML Tutorial' link in the search results$")
    public void Click_the__HTML_Tutorial__link_in_the_se() throws Throwable {
        // TODO: implement step
    }

    @Given("^Take a screenshot of the 'HTML Tutorial' article page$")
    public void Take_a_screenshot_of_the__HTML_Tutorial() throws Throwable {
        // TODO: implement step
    }
}