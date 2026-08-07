@WikipediaSearch
Feature: Search Wikipedia for a term and verify results

  As a Wikipedia user
  I want to search for a term
  So that I can find relevant information

  Scenario: Search for 'Playwright' and verify results
    Given I navigate to "https://www.wikipedia.org"
    When I execute step 1: "Navigate to https://www.wikipedia.org"
    When I execute step 2: "Type 'Playwright' into search"
    When I execute step 3: "Press Enter"
    When I execute step 4: "Verify the article heading shows 'Playwright'"
    Then the test should complete successfully