@WikipediaSearch
Feature: Wikipedia Search

  Scenario: Search and view Wikipedia article
    Given I navigate to Wikipedia
    When I search for "HTML"
    Then I should see the search results for "HTML"
    When I click the HTML link in search results
    Then I should see the HTML article page

  Scenario: Search with no input
    Given I navigate to Wikipedia
    When I search for ""
    Then I should see the search results for ""

  Scenario: Search with special characters
    Given I navigate to Wikipedia
    When I search for "!@#$%^&*()_+"
    Then I should see the search results for "!@#$%^&*()_+"