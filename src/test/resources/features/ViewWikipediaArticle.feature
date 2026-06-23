@ViewWikipediaArticle
Feature: View Wikipedia Article

  As a user
  I want to search for and view articles on Wikipedia
  So that I can learn about various topics

  Scenario: View Wikipedia Article for a Given Topic
    Given I navigate to "https://www.wikipedia.org/"
    When I search for "Python programming language" in the Wikipedia search field
    And I click the "Python (programming language)" link in the search results
    Then the page title should contain "Python (programming language)"

  Scenario: Invalid Input - Empty Search Field
    Given I navigate to "https://www.wikipedia.org/"
    When I search for "" in the Wikipedia search field
    Then the search results should be empty

  Scenario: Boundary Case - Maximum Search Query Length
    Given I navigate to "https://www.wikipedia.org/"
    When I search for "This is a very long search query that exceeds the maximum length of 300 characters. It is used to test the boundary case for the maximum allowed search query length on Wikipedia. This string should be exactly 300 characters long." in the Wikipedia search field
    Then the search results should contain "This is a very long search query that exceeds the maximum length of 300 characters. It is used to test the boundary case for the maximum allowed search query length on Wikipedia. This string should be exactly 300 characters long."

  Scenario: Search for Non-Existent Topic
    Given I navigate to "https://www.wikipedia.org/"
    When I search for "Non-Existent Topic" in the Wikipedia search field
    Then the search results should contain "No results found"