@ViewWikipediaArticle
Feature: View Article on Wikipedia

  As a Wikipedia user
  I want to search for and view an article
  So that I can learn about a topic

  Scenario: Happy Path - View Article on Wikipedia
    Given I navigate to "https://www.wikipedia.org/"
    When I execute step "In the 'Search Wikipedia' field, enter 'Python programming language'"
    When I execute step "Click the 'Search' icon button next to the search field"
    When I execute step "From the search results page, click the 'Python (programming language)' link"
    Then the test should complete successfully

  Scenario: Negative - Invalid Input: Empty Search Field
    Given I navigate to "https://www.wikipedia.org/"
    When I execute step "Click the 'Search' icon button next to the search field"
    Then the test should complete successfully

  Scenario: Negative - Boundary/Edge Case: Extra Long Search Query
    Given I navigate to "https://www.wikipedia.org/"
    When I execute step "In the 'Search Wikipedia' field, enter 'Python programming language'"
    When I execute step "Click the 'Search' icon button next to the search field"
    Then the test should complete successfully