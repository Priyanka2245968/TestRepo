@ViewArticle
Feature: View Article on Wikipedia

  Scenario: Search for and view HTML article
    Given I navigate to Wikipedia homepage
    When I search for "HTML"
    And I click on the "HTML" link
    Then I should see the "HTML - Wikipedia" article page

  Scenario: Search with blank input
    Given I navigate to Wikipedia homepage
    When I search for ""
    Then I should see an error message "Please enter a valid search term"

  Scenario: Search with 300 character string
    Given I navigate to Wikipedia homepage
    When I search for "This is a very long string with exactly 300 characters. This is a very long string with exactly 300 characters. This is a very long string with exactly 300 characters. This is a very long string with exactly 300 characters. This is a very long string with exactly 300 characters."
    Then I should see search results for the truncated search