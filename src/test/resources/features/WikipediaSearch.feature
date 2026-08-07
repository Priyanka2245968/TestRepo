@WikipediaSearch
Feature: Search Wikipedia for a term and verify results

  As a user
  I want to search for a term on Wikipedia
  So that I can find relevant information

  Scenario: Search returns results
    Given I navigate to "https://www.wikipedia.org"
    When I execute step 1: "Navigate to https://www.wikipedia.org"
    When I execute step 2: "Type 'Playwright' into the search input"
    When I execute step 3: "Press Enter to search"
    When I execute step 4: "Verify the article page shows the heading 'Playwright'"
    Then the test should complete successfully