@ViewArticle
Feature: View Article on Wikipedia

  Scenario: Happy Path - Search for and View Wikipedia Article
    Given I navigate to "https://www.wikipedia.org"
    When I search for "HTML Tables" on Wikipedia
    And I click the HTML table link
    Then I should see the HTML table article page
