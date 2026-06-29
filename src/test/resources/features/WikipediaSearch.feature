@WikipediaSearch
Feature: Search and View Article on Wikipedia

  Scenario: Search for and view Python programming language article
    Given I navigate to Wikipedia
    When I search for "Python programming language"
    And I click the Python link
    Then I should see the Python article page
