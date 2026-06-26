@ViewWikipediaArticle
Feature: View Article on Wikipedia

  As a Wikipedia user
  I want to search for and view an article
  So that I can learn about a topic

  Scenario: Happy Path - View Article on Wikipedia
    Given I navigate to Wikipedia
    When I search for "Python programming language"
    And I open the "Python (programming language)" article
    Then the "Python (programming language)" article page should be loaded

  Scenario: Negative - Invalid Input: Empty Search Field
    Given I navigate to Wikipedia
    When I search for ""
    Then the "Wikipedia" article page should be loaded

  Scenario: Negative - Boundary/Edge Case: Extra Long Search Query
    Given I navigate to Wikipedia
    When I search for "Python programming language Python programming language Python programming language Python programming language Python programming language Python programming language Python programming language Python programming language Python programming language Python programming language"
    Then the "Wikipedia" article page should be loaded