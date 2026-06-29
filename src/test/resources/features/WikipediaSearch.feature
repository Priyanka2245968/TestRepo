@WikipediaSearch
Feature: Wikipedia Search

  Scenario: Search for and view a Wikipedia article
    Given I navigate to Wikipedia
    When I search for "HTML"
    Then I should see search results
    When I click the first search result
    Then I should see the "HTML" article page

  Scenario: Empty search field
    Given I navigate to Wikipedia
    When I search for ""
    Then I should see no search results

  Scenario: Long search term
    Given I navigate to Wikipedia
    When I search for "A string of 500 characters"
    Then I should see search results