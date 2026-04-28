package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class HappyPathSearchAndViewWikipediaArticleSteps {
    private WebDriver driver;
    private HappyPathSearchAndViewWikipediaArticlePage page;

    public HappyPathSearchAndViewWikipediaArticleSteps() {
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

    @Given("^Click the magnifier / search icon button$")
    public void Click_the_magnifier___search_icon_button() throws Throwable {
        // TODO: implement step
    }

    @Given("^From the search results list, click the first link titled 'Python (programming language)'$")
    public void From_the_search_results_list__click_the() throws Throwable {
        // TODO: implement step
    }

    @Given("^Observe the 'Python (programming language)' article page$")
    public void Observe_the__Python__programming_languag() throws Throwable {
        // TODO: implement step
    }

    @Given("^In the left panel, click on the 'History' link under the 'Contents' section$")
    public void In_the_left_panel__click_on_the__History() throws Throwable {
        // TODO: implement step
    }
}