package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testNegativeSearchTextExceedsMaximumLength() {
        page.navigate("https://www.wikipedia.org/");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        String longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu lacus. Quisque ut nisi.";
        pageObject.searchInput.fill(longText);
        pageObject.searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("A text longer than 500 characters - Search results - Wikipedia");
        assertThat(pageObject.errorMessage).containsText("An error has occurred while searching: Search request is longer than the maximum allowed length");
        pageObject.takeScreenshot("negative-search-text-exceeds-max-length.png");
    }

    @Test
    public void testNegativeInvalidSearchTextProvided() {
        page.navigate("https://www.wikipedia.org/");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        String invalidText = "!@#$%^&*";
        pageObject.searchInput.fill(invalidText);
        pageObject.searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("!@#$%^&* - Search results - Wikipedia");
        assertThat(pageObject.errorMessage).containsText("There were no results matching the query");
        pageObject.takeScreenshot("negative-invalid-search-text.png");
    }
}
