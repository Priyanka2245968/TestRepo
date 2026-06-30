@WikipediaSearch
Feature: Wikipedia Search

  Scenario: Search and View Wikipedia Article
    Given I navigate to Wikipedia
    When I click the Search Wikipedia button
    And I enter 'HTML' in the Search Wikipedia field
    And I click the Search Wikipedia button
    Then the search results page should be displayed

  Scenario: Invalid Input - Search Field Empty
    Given I navigate to Wikipedia
    When I click the Search Wikipedia button
    And I enter '' in the Search Wikipedia field
    And I click the Search Wikipedia button
    Then an error message 'Please enter a search term' should be displayed

  Scenario: Boundary Case - Search Term Length
    Given I navigate to Wikipedia
    When I click the Search Wikipedia button
    And I enter '500-character-long-string' in the Search Wikipedia field
    Then the search field should contain '500-character-long-string...'