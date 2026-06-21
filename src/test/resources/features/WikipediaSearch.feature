@web
Feature: Wikipedia Search

  Scenario: Search for and View Article on Wikipedia
    Given I am on the Wikipedia homepage
    When I enter "HTML" in the search field
    And I click the search button
    Then I should see the search results for "HTML"
    And I click the "HTML" link
    Then I should see the "HTML" page

  Scenario: Invalid Input - Blank Search Term
    Given I am on the Wikipedia homepage
    When I click the search button
    Then I should see an error message "Please enter a search query"

  Scenario: Boundary/Edge Case - Long Search Query
    Given I am on the Wikipedia homepage
    When I enter "A very long query of 500+ characters" in the search field
    And I click the search button
    Then I should see the search results for "A very long query of 500+ characters"