@ViewWikipediaArticle
Feature: View Article on Wikipedia

  As a user
  I want to search for and view an article on Wikipedia
  So that I can learn about a topic

  Scenario: Happy Path - View Article on Wikipedia
    Given I navigate to "https://www.wikipedia.org/"
    When I execute step 1: "Navigate to https://www.wikipedia.org/"
    When I execute step 2: "In the 'Search Wikipedia' field, enter 'Python programming language'"
    When I execute step 3: "Click the 'Search Wikipedia' button or press Enter"
    When I execute step 4: "From the search results, click the 'Python (programming language)' link"
    When I execute step 5: "Verify the article page has loaded successfully"
    Then the test should complete successfully